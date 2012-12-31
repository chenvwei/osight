/*
 * Created on 2012-12-25
 */
package com.osight.history.service;

import java.util.List;

import com.osight.framework.pojos.AbstractModel;
import com.osight.history.ObjectHistory;
import com.osight.history.vo.HistoryDetail;
import com.osight.history.vo.HistoryInfo;

/**
 * @author chenw
 * @version $Id$
 */
public interface HistoryService {
    void save(HistoryInfo info);

    /**
     * 返回对象某条记录所对应的所有历史版本
     * 
     * @param c
     *            对象类
     * @param primaryKey
     *            主键
     * @param currentObj
     *            对象的当前版本, 只有中途加入对象日志的才需要传入此参数
     * @return 集合中的对象为c的实例
     */
    List<ObjectHistory<AbstractModel>> getVersionObjects(Class<? extends AbstractModel> c, long primaryKey,
            AbstractModel currentObj);

    /**
     * 返回对象某条记录所对应的所有历史版本
     * 
     * @param c
     *            对象类
     * @param primaryKey
     *            主键
     * @return 集合中的对象为c的实例
     */
    List<ObjectHistory<AbstractModel>> getVersionObjects(Class<? extends AbstractModel> c, long primaryKey);

    HistoryDetail getHistoryDetail(Class<? extends AbstractModel> c, long primaryKey, String versionId);

    List<HistoryDetail> getHistoryDetails(Class<? extends AbstractModel> c, long primaryKey);
}
