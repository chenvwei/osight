/*
 * Created on 2012-12-25
 */
package com.osight.history.dao.hibernate;

import java.util.List;

import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;
import org.springframework.stereotype.Repository;

import com.osight.framework.hibernate.BaseHibernateDAO;
import com.osight.history.dao.HistoryDAO;
import com.osight.history.pojo.HistoryLogData;

/**
 * @author chenw
 * @version $Id$
 */
@Repository("historyDao")
public class HistoryDAOImpl extends BaseHibernateDAO implements HistoryDAO {

    private static final String SEL_VERSIONLOG = "select p from HistoryLogData p ";
    private static final String CON_CLASSNAME = " p.tableName=? ";
    private static final String CON_PRIMARYKEY = " p.primaryKey=? ";
    private static final String CON_VERSIONID = " p.version=? ";
    private static final String ORDER_BY_LOGTIME = " order by p.logTime desc ";

    public void save(HistoryLogData log) {
        // 只允许insert
        if (log.getId() != 0)
            throw new IllegalArgumentException("日志操作只允许insert");
        hibernateUtil.save(log);
    }

    public List<HistoryLogData> getVersionLogs(String className, long primaryKey, String versionId) {
        List<HistoryLogData> l = hibernateUtil.find(SEL_VERSIONLOG + " where " + CON_CLASSNAME + " and " + CON_PRIMARYKEY + " and "
                + CON_VERSIONID + ORDER_BY_LOGTIME, new Object[] {className, primaryKey, versionId}, new Type[] {
                StringType.INSTANCE, LongType.INSTANCE, StringType.INSTANCE});
        return l;
    }

    public List<HistoryLogData> getVersionLogs(String className, long primaryKey) {
        List<HistoryLogData> l = hibernateUtil.find(
                SEL_VERSIONLOG + " where " + CON_CLASSNAME + " and " + CON_PRIMARYKEY + ORDER_BY_LOGTIME, new Object[] {
                        className, primaryKey}, new Type[] {StringType.INSTANCE, LongType.INSTANCE});
        return l;
    }

}
