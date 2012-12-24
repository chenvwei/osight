/*
 * Created on 2012-12-24
 */
package com.sight.core.test;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.osight.core.pojos.UserData;
import com.osight.core.service.UserService;
import com.osight.core.service.UserServiceFactory;

/**
 * @author chenw <a href="mailto:chenw@chsi.com.cn">chen wei</a>
 * @version $Id$
 */
public class UserServiceTest {
    private Logger log = LoggerFactory.getLogger(getClass());

    @Test
    public void test() {

    }

    @Test
    public void testNew() {
        UserService userSerivce = UserServiceFactory.getUserService();
        UserData user = userSerivce.createUser("rodneytt", "rodneytt@sina.com", null, "123456");
        log.info(ToStringBuilder.reflectionToString(user, ToStringStyle.MULTI_LINE_STYLE));
    }
}
