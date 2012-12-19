/*
 * Created on 2012-11-19
 */
package com.osight.framework.util;

import java.util.UUID;

import org.apache.commons.lang.StringUtils;

/**
 * @author chenw 
 * @version $Id$
 */
public class UUIDUtil {
    /**
     * 获取一个随机的uuid字符串，32字节
     * 
     * @return
     */
    static public String getRandomUUID() {
        String uuid = UUID.randomUUID().toString();
        uuid = StringUtils.replace(uuid, "-", "");

        return uuid;
    }
}
