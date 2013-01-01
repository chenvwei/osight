/*
 * Created on 2012-11-22
 */
package com.osight.core.dao.impl;

import java.util.List;

import org.hibernate.type.LongType;
import org.hibernate.type.Type;
import org.springframework.stereotype.Repository;

import com.osight.core.dao.LotteryDAO;
import com.osight.core.pojos.LotteryData;
import com.osight.core.pojos.LotteryNumberData;
import com.osight.framework.hibernate.BaseHibernateDAO;

/**
 * @author chenw
 * @version $Id$
 */
@Repository("lotteryDao")
public class LotteryDAOImpl extends BaseHibernateDAO implements LotteryDAO {

    @Override
    public LotteryData saveOrUpdate(LotteryData data) {
        if (data.getId() == 0) {
            hibernateUtil.save(data);
        } else {
            hibernateUtil.update(data);
        }
        return data;
    }

    @Override
    public LotteryNumberData saveOrUpdate(LotteryNumberData data) {
        if (data.getId() == 0) {
            hibernateUtil.save(data);
        } else {
            hibernateUtil.update(data);
        }
        return data;
    }

    @Override
    public List<LotteryNumberData> getLotteryNumberByLotteryId(long lotteryId) {
        List<LotteryNumberData> list = hibernateUtil.find("select p from LotteryNumberData p where p.lotteryId=?",
                new Object[] {lotteryId}, new Type[] {LongType.INSTANCE});
        return list;
    }
}
