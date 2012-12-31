/*
 * Created on 2012-12-25
 */
package com.osight.history.listener;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.event.spi.AbstractEvent;
import org.hibernate.event.spi.EventSource;
import org.hibernate.event.spi.PostDeleteEvent;
import org.hibernate.event.spi.PostDeleteEventListener;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.event.spi.PostUpdateEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.osight.framework.hibernate.ThreadLocalManager;
import com.osight.framework.invoke.InvokeInfoHelper;
import com.osight.framework.pojos.AbstractModel;
import com.osight.framework.pojos.AuditableObject;
import com.osight.framework.service.IService;
import com.osight.framework.transaction.TransactionIdHelper;
import com.osight.framework.util.UUIDUtil;
import com.osight.history.annotation.HistoryProp;
import com.osight.history.annotation.NoHistory;
import com.osight.history.service.HistoryService;
import com.osight.history.vo.HistoryInfo;

/**
 * @author chenw
 * @version $Id$
 */
public class HistoryEventListener implements PostInsertEventListener, PostUpdateEventListener, PostDeleteEventListener {

    public static final String SUFIX_VER = "_versionid";

    private static final long serialVersionUID = 1L;
    protected Logger log = LoggerFactory.getLogger(getClass());
    private boolean verbose = true;
    private HistoryService historyService;

    private String historyServiceClassName;

    public void onPostInsert(PostInsertEvent event) {
        try {
            boolean versionable = isHistoryEntity(event.getEntity(), true);

            if (versionable) {
                AbstractModel entity = (AbstractModel) event.getEntity();
                String[] names = event.getPersister().getPropertyNames();
                Object[] state = event.getState();
                Map<String, Object> map = makeMap(names, state);

                String entityName = entity.getClass().getName();
                long entityId = entity.getId();

                String verId = getVersionId(event);
                logChanges(map, null, null, entityId, "INSERT", entityName, verId, entity);
            }
        } catch (HibernateException e) {
            log.error("Audit Plugin unable to process INSERT event", e);
        }
    }

    public void onPostUpdate(PostUpdateEvent event) {
        try {
            boolean versionable = isHistoryEntity(event.getEntity(), false);

            if (versionable) {
                AbstractModel entity = (AbstractModel) event.getEntity();
                String[] names = event.getPersister().getPropertyNames();
                Object[] oldState = event.getOldState();
                Object[] newState = event.getState();
                Map<String, Object> newMap = makeMap(names, newState);
                Map<String, Object> oldMap = makeMap(names, oldState);

                String entityName = entity.getClass().getName();
                long entityId = entity.getId();
                String verId = getVersionId(event);
                logChanges(newMap, oldMap, null, entityId, "UPDATE", entityName, verId, entity);
            }
        } catch (HibernateException e) {
            log.error("Audit Plugin unable to process UPDATE event", e);
        }
    }

    public void onPostDelete(PostDeleteEvent event) {
        try {
            boolean versionable = isHistoryEntity(event.getEntity(), false);

            if (versionable) {
                AbstractModel entity = (AbstractModel) event.getEntity();
                String[] names = event.getPersister().getPropertyNames();
                Object[] state = event.getDeletedState();
                Map<String, Object> map = makeMap(names, state);

                String entityName = entity.getClass().getName();
                long entityId = entity.getId();
                String verId = getVersionId(event);
                logChanges(null, map, null, entityId, "DELETE", entityName, verId, entity);
            }
        } catch (HibernateException e) {
            log.error("Audit Plugin unable to process DELETE event", e);
        }
    }

    /**
     * 
     * @param entry
     * @param insertOp
     *            是否为insert操作
     * @return
     */
    private boolean isHistoryEntity(Object entry, boolean insertOp) {
        boolean versionable = false;

        Class<HistoryProp> verClass = HistoryProp.class;
        HistoryProp ver = (HistoryProp) entry.getClass().getAnnotation(verClass);
        if (null == ver) {
            versionable = false;
        } else {
            if (insertOp) {
                if (ver.historyInsert()) {
                    versionable = true;
                } else {
                    // 不记录insert日志
                    versionable = false;
                }
            } else
                versionable = true;
        }

        return versionable;
    }

    private Map<String, Object> makeMap(String[] names, Object[] state) {
        Map<String, Object> map = new HashMap<String, Object>();
        for (int ii = 0; ii < names.length; ii++) {
            map.put(names[ii], state[ii]);
        }
        return map;
    }

    public static String getHistoryThreadResKey(EventSource session) {
        return session.hashCode() + SUFIX_VER;
    }

    private String getVersionId(AbstractEvent event) {
        String versionId = (String) ThreadLocalManager.getResource(getHistoryThreadResKey(event.getSession()));
        if (null == versionId) {
            versionId = UUIDUtil.getRandomUUID();
        }

        return versionId;
    }

    /**
     * Leans heavily on the "toString()" of a property ... this feels crufty... should be tighter...
     */
    private void logChanges(Map<String, Object> newMap, Map<String, Object> oldMap, Object parentObject,
            long persistedObjectId, String eventName, String className, String versionId, Object entry) {

        String persistedObjectVersion = versionId;
        String transactionId = TransactionIdHelper.getTranactionId();

        HistoryInfo ver = null;
        Calendar now = Calendar.getInstance();
        String userid = getCurrentUser();

        if (null != newMap && null != oldMap) {// update
            Iterator<Map.Entry<String, Object>> itr = newMap.entrySet().iterator();
            while (itr.hasNext()) {
                Map.Entry<String, Object> newEntry = (Map.Entry<String, Object>) itr.next();
                String key = (String) newEntry.getKey();
                if (!isHistoryProp(entry, key, OpType.UPDATE))
                    continue;

                Object newValueO = newEntry.getValue();
                Object oldValueO = oldMap.get(key);

                boolean oldValueEqualNewValue = false;
                if ((null == newValueO || "".equals(newValueO.toString()))
                        && (null == oldValueO || "".equals(newValueO.toString()))) {
                    oldValueEqualNewValue = true;
                } else if (((null == newValueO) && (null != oldValueO)) || ((null != oldValueO) && (null == oldValueO))) {
                    oldValueEqualNewValue = false;
                    if (oldValueO instanceof AbstractModel) {
                        oldValueO = ((AbstractModel) oldValueO).getId();
                    }
                    if (newValueO instanceof AbstractModel) {
                        newValueO = ((AbstractModel) newValueO).getId();
                    }
                } else {
                    if (oldValueO instanceof Calendar) {
                        ((Calendar) oldValueO).setLenient(true);
                        ((Calendar) newValueO).setLenient(true);
                    }
                    if (oldValueO instanceof AbstractModel) {
                        oldValueO = ((AbstractModel) oldValueO).getId();
                    }
                    if (newValueO instanceof AbstractModel) {
                        newValueO = ((AbstractModel) newValueO).getId();
                    }
                    oldValueEqualNewValue = newValueO.equals(oldValueO);
                }

                if (!oldValueEqualNewValue) {
                    ver = new HistoryInfo();
                    ver.setTransactionId(transactionId);
                    ver.setLogTime(now);
                    ver.setOperationType(eventName);
                    ver.setLogBy(userid);
                    ver.setLogUserIp(getCurrentUserIp());
                    ver.setVersion(persistedObjectVersion);

                    ver.setNewValue(newValueO);
                    ver.setOldValue(oldValueO);
                    ver.setPrimaryKey(persistedObjectId);
                    ver.setTableName(className);
                    ver.setFieldName(key);
                    save(ver);
                }
            }
        } else if (null != newMap && verbose) {// insert
            Iterator<Map.Entry<String, Object>> itr = newMap.entrySet().iterator();
            while (itr.hasNext()) {
                Map.Entry<String, Object> newEntry = (Map.Entry<String, Object>) itr.next();
                String key = (String) newEntry.getKey();
                if (!isHistoryProp(entry, key, OpType.INSERT))
                    continue;

                Object newValueO = newEntry.getValue();
                if (newValueO instanceof AbstractModel) {
                    newValueO = ((AbstractModel) newValueO).getId();
                }
                ver = new HistoryInfo();
                ver.setTransactionId(transactionId);
                ver.setLogTime(now);
                ver.setOperationType(eventName);
                ver.setLogBy(userid);
                ver.setLogUserIp(getCurrentUserIp());
                ver.setVersion(persistedObjectVersion);

                ver.setNewValue(newValueO);
                ver.setOldValue(null);
                ver.setPrimaryKey(persistedObjectId);
                ver.setTableName(className);
                ver.setFieldName(key);
                save(ver);
            }
        } else if (null != oldMap && verbose) {// delete
            Iterator<Map.Entry<String, Object>> itr = oldMap.entrySet().iterator();
            while (itr.hasNext()) {
                Map.Entry<String, Object> oldEntry = (Map.Entry<String, Object>) itr.next();

                String key = (String) oldEntry.getKey();
                if (!isHistoryProp(entry, key, OpType.DELETE))
                    continue;

                Object oldValueO = oldEntry.getValue();
                if (oldValueO instanceof AbstractModel) {
                    oldValueO = ((AbstractModel) oldValueO).getId();
                }

                ver = new HistoryInfo();
                ver.setTransactionId(transactionId);
                ver.setLogTime(now);
                ver.setOperationType(eventName);
                ver.setLogBy(userid);
                ver.setLogUserIp(getCurrentUserIp());
                ver.setVersion(persistedObjectVersion);

                ver.setNewValue(null);
                ver.setOldValue(oldValueO);
                ver.setPrimaryKey(persistedObjectId);
                ver.setTableName(className);
                ver.setFieldName(key);
                save(ver);
            }
        } else {
            // 非详细日志
            ver = new HistoryInfo();
            ver.setTransactionId(transactionId);
            ver.setLogTime(now);
            ver.setOperationType(eventName);
            ver.setLogBy(userid);
            ver.setLogUserIp(getCurrentUserIp());
            ver.setVersion(persistedObjectVersion);

            ver.setPrimaryKey(persistedObjectId);
            ver.setTableName(className);
            save(ver);
        }
    }

    private boolean isHistoryProp(Object entry, String propName, OpType type) {
        boolean versionable = false;
        Field fld = null;
        Class<?> clazz = entry.getClass();

        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                fld = clazz.getDeclaredField(propName);
                if (fld != null)
                    break;
            } catch (SecurityException e) {
                throw new RuntimeException(e);
            } catch (NoSuchFieldException e) {
            }
        }

        if (fld == null) {
            throw new RuntimeException(new NoSuchFieldException(propName));
        }
        if (fld.getDeclaringClass() == AuditableObject.class) {
            return false;
        }
        Class<NoHistory> verClass = NoHistory.class;
        NoHistory ver = (NoHistory) fld.getAnnotation(verClass);
        if (null == ver) {
            versionable = true;
        } else {
            switch (type) {
            case INSERT:
                versionable = !ver.isNoInsertHistory();
                break;
            case UPDATE:
                versionable = !ver.isNoUpdateHistory();
                break;
            case DELETE:
                versionable = !ver.isNoDeleteHistory();
                break;
            default:
                throw new IllegalArgumentException();
            }
        }

        return versionable;
    }

    private String getCurrentUser() {
        return InvokeInfoHelper.getCurrentUser();
    }

    private String getCurrentUserIp() {
        return InvokeInfoHelper.getCurrentUserIp();
    }

    void save(HistoryInfo verInfo) {
        if (null == historyService) {
            // 这段代码之所以不放在setHistoryServiceName中，是因为要避免BeanLocator的内部循环创建问题
            // 主要是单元测试时使用
            Class<?> c;
            try {
                c = Class.forName(historyServiceClassName);
                historyService = (HistoryService) c.newInstance();
                ((IService) historyService).create();
            } catch (ClassNotFoundException e) {
                log.error("设置historyService出错", e);
            } catch (InstantiationException e) {
                log.error("设置historyService出错", e);
            } catch (IllegalAccessException e) {
                log.error("设置historyService出错", e);
            }
        }
        historyService.save(verInfo);
    }

    public void setHistoryService(HistoryService historyService) {
        this.historyService = historyService;
    }

    /**
     * 主要是在单元测试中使用（在spring配置文件中指定）
     * 
     * @param className
     */
    public void setHistoryServiceName(String className) {
        historyServiceClassName = className;
        if (log.isInfoEnabled())
            log.info("设置historyServiceName:" + historyServiceClassName);
    }
}

enum OpType {
    INSERT,
    UPDATE,
    DELETE
}