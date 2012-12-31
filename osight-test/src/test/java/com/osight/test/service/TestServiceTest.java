/*
 * Created on 2012-12-31
 */
package com.osight.test.service;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.osight.test.pojo.TestPojoData;

/**
 * @author chenw
 * @version $Id$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-config.xml"})
@Transactional
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
public class TestServiceTest {
    private Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    @Qualifier("testService")
    private TestService testService;

    @Test
    public void test() {
        TestPojoData pojo = testService.newPojo("chenwei", "男", "rodneytt@sina.com");
        log.info(ToStringBuilder.reflectionToString(pojo, ToStringStyle.SHORT_PREFIX_STYLE));
        
        TestPojoData p = testService.gePojoById(pojo.getId());
        log.info(ToStringBuilder.reflectionToString(p, ToStringStyle.MULTI_LINE_STYLE));
        
    }
}
