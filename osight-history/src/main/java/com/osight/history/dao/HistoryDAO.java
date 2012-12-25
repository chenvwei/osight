/*
 * Created on 2012-12-25
 */
package com.osight.history.dao;

import java.util.List;

import com.osight.history.pojo.HistoryLogData;

/**
 * @author chenw
 * @version $Id$
 */
public interface HistoryDAO {
    void save(HistoryLogData log);

    List<HistoryLogData> getVersionLogs(String className, long primaryKey);

    List<HistoryLogData> getVersionLogs(String className, long primaryKey, String versionId);
}
