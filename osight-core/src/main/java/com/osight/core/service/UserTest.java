/*
 * Created on 2012-11-19
 */
package com.osight.core.service;

import java.util.Calendar;

import com.osight.core.pojos.LotteryData;

/**
 * @author chenw
 * @version $Id$
 */
public class UserTest {
    public static void main(String[] args) {
        LotteryService lservice = LotteryServiceFactory.getLotteryService();
        LotteryData data = lservice.newLottery(123, Calendar.getInstance().getTime(), "123456789");
        System.out.println(data.getId());
    }
}
