/*
 * Created on 2012-11-22
 */
package com.osight.core.service.impl;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import com.osight.core.dao.LotteryDAO;
import com.osight.core.pojos.LotteryData;
import com.osight.core.pojos.LotteryNumberData;
import com.osight.core.service.LotteryService;
import com.osight.framework.exception.ArgumentException;
import com.osight.framework.service.BaseDbService;

/**
 * @author chenw
 * @version $Id$
 */
public class LotteryServiceImpl extends BaseDbService implements LotteryService {

    private LotteryDAO lotteryDAO;

    @Override
    public LotteryData newLottery(int no, Date date, String number) {
        Pattern rsPtn = Pattern.compile("(\\d{2} ){6}\\+ \\d{2}");
        if (!rsPtn.matcher(number).matches()) {
            throw new ArgumentException("开奖号码格式不正确");
        }
        LotteryData data = new LotteryData();
        data.setIssueNo(no);
        data.setDate(date);
        data.setNumber(number);
        lotteryDAO.saveOrUpdate(data);

        String[] arr = number.replace("+ ", "").split(" ");
        for (int i = 0; i < arr.length; i++) {
            LotteryNumberData d = new LotteryNumberData();
            d.setLotteryId(data.getId());
            d.setIndex(i + 1);
            int num = Integer.parseInt(arr[i]);
            d.setNumber(num);
            if (i == arr.length - 1) {
                d.setName("红球");
            } else {
                d.setName("蓝球");
            }
            lotteryDAO.saveOrUpdate(d);
        }

        return data;
    }

    @Override
    protected void doCreate() {
        lotteryDAO = (LotteryDAO) getDAO("lotteryDAO", LotteryDAO.class);
    }

    @Override
    protected void doRemove() {
    }

    @Override
    public List<LotteryNumberData> getLotteryNumberByLotteryId(long lotteryId) {
        return lotteryDAO.getLotteryNumberByLotteryId(lotteryId);
    }

}
