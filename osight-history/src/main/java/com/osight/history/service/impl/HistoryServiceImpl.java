/*
 * Created on 2012-12-25
 */
package com.osight.history.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import com.osight.framework.pojos.AbstractModel;
import com.osight.framework.service.BaseDbService;
import com.osight.history.ObjectHistory;
import com.osight.history.dao.HistoryDAO;
import com.osight.history.pojo.HistoryLogData;
import com.osight.history.service.HistoryService;
import com.osight.history.vo.HistoryDetail;
import com.osight.history.vo.HistoryInfo;

/**
 * @author chenw
 * @version $Id$
 */
public class HistoryServiceImpl extends BaseDbService implements HistoryService {
    private HistoryDAO historyDAO;

    @Override
    public void save(HistoryInfo info) {
        if (info.isOneTablePerField()) {
            throw new IllegalArgumentException("目前还不支持");
        }

        HistoryLogData vl = new HistoryLogData();
        vl.convertFrom(info);

        historyDAO.save(vl);
    }

    @Override
    public List<ObjectHistory<AbstractModel>> getVersionObjects(Class<? extends AbstractModel> c, long primaryKey,
            AbstractModel currentObj) {
        List<HistoryDetail> verDetails = getHistoryDetails(c, primaryKey);

        List<ObjectHistory<AbstractModel>> versions = new ArrayList<ObjectHistory<AbstractModel>>();

        if (verDetails.size() == 0) {
            return versions;
        }

        boolean first = true;
        AbstractModel prev = null;
        try {
            prev = c.newInstance();
        } catch (InstantiationException e) {
            log.error("生产类实例错误:" + c.getName(), e);
            throw new IllegalArgumentException("生产类实例错误:" + c.getName());
        } catch (IllegalAccessException e) {
            log.error("生产类实例错误:" + c.getName(), e);
            throw new IllegalArgumentException("生产类实例错误:" + c.getName());
        }

        /**
         * 是否包含了原始的数据信息（是否在日志里面捕获了该对象的INSERT消息）
         */
        boolean hasOriginInfo = verDetails.get(0).getOperationType().equals("INSERT");
        boolean isDeleted = verDetails.get(verDetails.size() - 1).getOperationType().equals("DELETE");

        if ((!hasOriginInfo) && (!isDeleted)) {
            if (null == currentObj) {
                throw new IllegalArgumentException("currentObj对象不能为空");
            }
        }

        if (hasOriginInfo) {
            int end = isDeleted ? verDetails.size() - 1 : verDetails.size();

            for (int i = 0; i < end; i++) {
                HistoryDetail value = verDetails.get(i);

                if (first) {
                    prev.setId(primaryKey);
                    first = false;
                }

                ObjectHistory<AbstractModel> objVer = new ObjectHistory<AbstractModel>();
                objVer.setTransactionId(value.getTransactionId());
                objVer.setLogBy(value.getLogBy());
                objVer.setLogTime(value.getLogTime());
                objVer.setVersion(value.getVersion());

                // 设置对象的初始值
                setNewProp(prev, value);
                objVer.setCurrVersionObject(clone(prev));
                versions.add(objVer);
            }
        } else {
            AbstractModel curr = null;
            if (isDeleted) {
                setOldProp(prev, verDetails.get(verDetails.size() - 1));
                prev.setId(primaryKey);
                curr = prev;
            } else {
                curr = clone(currentObj);
            }

            int start = isDeleted ? verDetails.size() - 2 : verDetails.size() - 1;
            int end = isDeleted ? 0 : 1;

            for (int i = start; i >= end; i--) {
                HistoryDetail value = verDetails.get(i);

                if (first) {
                    ObjectHistory<AbstractModel> objVer = new ObjectHistory<AbstractModel>();
                    objVer.setTransactionId(value.getTransactionId());
                    objVer.setLogBy(value.getLogBy());
                    objVer.setLogTime(value.getLogTime());
                    objVer.setVersion(value.getVersion());

                    objVer.setCurrVersionObject(clone(curr));
                    versions.add(objVer);
                    first = false;
                }

                ObjectHistory<AbstractModel> objVer = new ObjectHistory<AbstractModel>();
                objVer.setTransactionId(value.getTransactionId());
                objVer.setLogBy(value.getLogBy());
                objVer.setLogTime(value.getLogTime());
                objVer.setVersion(value.getVersion());

                // 设置对象的初始值
                setOldProp(curr, value);

                objVer.setCurrVersionObject(clone(curr));
                versions.add(objVer);
            }

            Collections.sort(versions);
        }

        return versions;
    }

    @Override
    public List<ObjectHistory<AbstractModel>> getVersionObjects(Class<? extends AbstractModel> c, long primaryKey) {
        return getVersionObjects(c, primaryKey, null);
    }

    @Override
    public HistoryDetail getHistoryDetail(Class<? extends AbstractModel> c, long primaryKey, String versionId) {
        String className = c.getName();
        List<HistoryLogData> l = historyDAO.getVersionLogs(className, primaryKey, versionId);
        
        HistoryDetail vo = new HistoryDetail();
        
        boolean first = true;
        for(HistoryLogData verPo : l){
            String key = verPo.getVersion();
            
            if(first){
                vo.setLogBy(verPo.getLogBy());
                vo.setLogTime(verPo.getLogTime());
                vo.setOperationType(verPo.getOperationType());
                vo.setVersion(key);
                first = false;
            }
            
            vo.getNames().add(verPo.getFieldName());
            vo.getNewValues().add(verPo.getNewValue());
            vo.getOldValues().add(verPo.getOldValue());
        }
        
        return vo;
    }

    @Override
    public List<HistoryDetail> getHistoryDetails(Class<? extends AbstractModel> c, long primaryKey) {
        String className = c.getName();
        List<HistoryLogData> l = historyDAO.getVersionLogs(className, primaryKey);
        
        //根据version和operationType分组，然后按时间排序
        List<HistoryDetail> verDetails = new ArrayList<HistoryDetail>();
        for(HistoryLogData verPo : l){
            String version = verPo.getVersion();
            String opType = verPo.getOperationType();
            HistoryDetail vo = null;
            for(HistoryDetail detail : verDetails){
                if(detail.getVersion().equals(version) && detail.getOperationType().equals(opType)){
                    vo = detail;
                }
            }
            
            if(null==vo){
                vo = new HistoryDetail();
                vo.setLogBy(verPo.getLogBy());
                vo.setLogTime(verPo.getLogTime());
                vo.setOperationType(opType);
                vo.setVersion(version);
                vo.setTransactionId(verPo.getTransactionId());
                verDetails.add(vo);
            }
            
            vo.getNames().add(verPo.getFieldName());
            vo.getNewValues().add(verPo.getNewValue());
            vo.getOldValues().add(verPo.getOldValue());
        }
        
        Collections.sort(verDetails);
        
        return verDetails;
    }

    @Override
    protected void doCreate() {
        historyDAO = (HistoryDAO) getDAO("historyDAO", HistoryDAO.class);
    }

    @Override
    protected void doRemove() {
    }

    private AbstractModel clone(AbstractModel o) {
        AbstractModel clone = null;
        try {
            clone = (AbstractModel) BeanUtils.cloneBean(o);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException("clone error! id=" + o.getId(), e);
        } catch (InstantiationException e) {
            throw new IllegalArgumentException("clone error! id=" + o.getId(), e);
        } catch (InvocationTargetException e) {
            throw new IllegalArgumentException("clone error! id=" + o.getId(), e);
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("clone error! id=" + o.getId(), e);
        }
        ;
        return clone;
    }

    private void setNewProp(Object o, HistoryDetail insertValue) {
        for (int i = 0; i < insertValue.getNames().size(); i++) {
            String propName = insertValue.getNames().get(i);
            String newValue = insertValue.getNewValues().get(i);
            Object newValueO = null;
            try {
                Class<?> c = PropertyUtils.getPropertyType(o, propName);
                newValueO = HistoryLogData.toObject(c, newValue);
            } catch (IllegalAccessException e1) {
                throw new IllegalArgumentException("属性转换错误! version=" + insertValue.getVersion() + ",class="
                        + insertValue.getClass(), e1);
            } catch (InvocationTargetException e1) {
                throw new IllegalArgumentException("属性转换错误! version=" + insertValue.getVersion() + ",class="
                        + insertValue.getClass(), e1);
            } catch (NoSuchMethodException e1) {
                throw new IllegalArgumentException("属性转换错误! version=" + insertValue.getVersion() + ",class="
                        + insertValue.getClass(), e1);
            }

            try {
                PropertyUtils.setProperty(o, propName, newValueO);
            } catch (IllegalAccessException e) {
                throw new IllegalArgumentException("设置属性错误! version=" + insertValue.getVersion() + ",class="
                        + insertValue.getClass(), e);
            } catch (InvocationTargetException e) {
                throw new IllegalArgumentException("设置属性错误! version=" + insertValue.getVersion() + ",class="
                        + insertValue.getClass(), e);
            } catch (NoSuchMethodException e) {
                throw new IllegalArgumentException("设置属性错误! version=" + insertValue.getVersion() + ",class="
                        + insertValue.getClass(), e);
            }
        }
    }

    private void setOldProp(Object o, HistoryDetail insertValue) {
        for (int i = 0; i < insertValue.getNames().size(); i++) {
            String propName = insertValue.getNames().get(i);
            String oldValue = insertValue.getOldValues().get(i);
            Object oldValueO = null;
            try {
                Class<?> c = PropertyUtils.getPropertyType(o, propName);
                oldValueO = HistoryLogData.toObject(c, oldValue);
            } catch (IllegalAccessException e1) {
                throw new IllegalArgumentException("属性转换错误! version=" + insertValue.getVersion() + ",class="
                        + insertValue.getClass(), e1);
            } catch (InvocationTargetException e1) {
                throw new IllegalArgumentException("属性转换错误! version=" + insertValue.getVersion() + ",class="
                        + insertValue.getClass(), e1);
            } catch (NoSuchMethodException e1) {
                throw new IllegalArgumentException("属性转换错误! version=" + insertValue.getVersion() + ",class="
                        + insertValue.getClass(), e1);
            }

            try {
                PropertyUtils.setProperty(o, propName, oldValueO);
            } catch (IllegalAccessException e) {
                throw new IllegalArgumentException("设置属性错误! version=" + insertValue.getVersion() + ",class="
                        + insertValue.getClass(), e);
            } catch (InvocationTargetException e) {
                throw new IllegalArgumentException("设置属性错误! version=" + insertValue.getVersion() + ",class="
                        + insertValue.getClass(), e);
            } catch (NoSuchMethodException e) {
                throw new IllegalArgumentException("设置属性错误! version=" + insertValue.getVersion() + ",class="
                        + insertValue.getClass(), e);
            } catch (Exception e) {
                throw new IllegalArgumentException("设置属性错误! prop=" + propName + ",value=" + oldValueO + ",class="
                        + insertValue.getClass(), e);
            }
        }
    }
}
