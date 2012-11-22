/*
 * Created on 2012-11-22
 */
package com.osight.core.util;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.osight.core.pojos.LotteryData;
import com.osight.core.pojos.LotteryNumberData;
import com.osight.core.service.LotteryService;
import com.osight.core.service.LotteryServiceFactory;

/**
 * @author chenw
 * @version $Id$
 */
public class ImportCaipiao {
    Logger log = LoggerFactory.getLogger(getClass());
    LotteryService ls = LotteryServiceFactory.getLotteryService();

    public static void main(String[] args) {
        ImportCaipiao ic = new ImportCaipiao();
        ic.importCP();
    }

    public void testInsert() {
        LotteryData data = ls.newLottery(123456, Calendar.getInstance().getTime(), "07 12 16 17 21 25 + 11");
        List<LotteryNumberData> list = ls.getLotteryNumberByLotteryId(data.getId());
        for (LotteryNumberData num : list) {
            log.info(num.toString());
        }

    }

    public void importCP() {
        try {
            Workbook wb = Workbook.getWorkbook(new File("F:\\develop\\projects\\osight\\doc\\caipiao.xls"));
            Sheet sheet = wb.getSheet(0);
            Cell[] dates = sheet.getColumn(0);
            Cell[] nos = sheet.getColumn(1);
            Cell[] numbers = sheet.getColumn(2);
            DateFormat df = new SimpleDateFormat("yyyy/M/d");
            for (int i = 0; i < dates.length; i++) {
                Date d = df.parse(dates[i].getContents());
                int no = Integer.parseInt("20" + nos[i].getContents());
                String number = numbers[i].getContents();
                ls.newLottery(no, d, number);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

}
