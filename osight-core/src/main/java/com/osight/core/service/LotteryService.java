/*
 * Created on 2012-11-22
 */
package com.osight.core.service;

import java.util.Date;
import java.util.List;

import com.osight.core.pojos.LotteryData;
import com.osight.core.pojos.LotteryNumberData;

/**
 * @author chenw
 * @version $Id$
 */
public interface LotteryService {
    public LotteryData newLottery(int no, Date date, String number);

    public List<LotteryNumberData> getLotteryNumberByLotteryId(long lotteryId);
}
