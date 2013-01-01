/*
 * Created on 2012-12-25
 */
package com.osight.history.service;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.osight.history.pojo.TestPojo;
import com.osight.history.vo.HistoryDetail;

/**
 * @author chenw
 * @version $Id$
 */
public class HistoryServiceTest {
    Logger log = LoggerFactory.getLogger(getClass());

    @Test
    public void test() {
    }

    @Test
    public void testNew() {
        TestPojoService testService = TestServiceFactory.getService();
        TestPojo data = testService.newPojo("rodneytt", "123455", 1, "rodneytt@sina.com");
        log.info("1:"+ToStringBuilder.reflectionToString(data, ToStringStyle.SIMPLE_STYLE));
        data = testService.updateEmail(data.getId(), "chenvvwei@163.com");
        log.info("2:"+ToStringBuilder.reflectionToString(data, ToStringStyle.SIMPLE_STYLE));
        HistoryService history = HistoryServiceFactory.getHistoryService();
        List<HistoryDetail> list = history.getHistoryDetails(TestPojo.class, data.getId());
        Assert.assertEquals(list.size(), 1);
        for (HistoryDetail obj : list) {
            log.info("3:"+ToStringBuilder.reflectionToString(obj, ToStringStyle.SHORT_PREFIX_STYLE));
        }
    }

    @Test
    public void testDelete() {
        TestPojoService testService = TestServiceFactory.getService();
        TestPojo data = testService.newPojo("rodneytt", "123455", 1, "rodneytt@sina.com");
        testService.delete(data.getId());
        HistoryService history = HistoryServiceFactory.getHistoryService();
        List<HistoryDetail> list = history.getHistoryDetails(TestPojo.class, data.getId());
        Assert.assertEquals(list.size(), 1);
    }
}
