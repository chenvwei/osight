/*
 * Created on 2012-11-22
 */
package com.osight.core.dao;

import java.util.List;

import com.osight.core.pojos.LotteryData;
import com.osight.core.pojos.LotteryNumberData;

/**
 * @author chenw 
 * @version $Id$
 */
public interface LotteryDAO {
    public LotteryData saveOrUpdate(LotteryData data);
    public LotteryNumberData saveOrUpdate(LotteryNumberData data);
    public List<LotteryNumberData> getLotteryNumberByLotteryId(long lotteryId);
}
