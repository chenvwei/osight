/*
 * Created on 2012-11-16
 */
package com.osight.framework.pojos;

import java.util.Calendar;

/**
 * @author chenw <a href="mailto:chenw@chsi.com.cn">chen wei</a>
 * @version $Id$
 */
public interface Auditable {
    /**
     * 创建者
     */
    String getCreatedBy();

    void setCreatedBy(String userid);

    /**
     * 创建时间
     * 
     * @return
     */
    Calendar getCreatedOn();

    void setCreatedOn(Calendar creationTime);

    /**
     * 创建者ip
     * 
     * @return
     */
    String getCreatedIp();

    void setCreatedIp(String ip);

    /**
     * 创建时所在的服务器
     * 
     * @return
     */
    String getCreatedServer();

    void setCreatedServer(String server);

    /**
     * 修改者
     * 
     * @return
     */
    String getUpdatedBy();

    void setUpdatedBy(String userid);

    /**
     * 修改时间
     * 
     * @return
     */
    Calendar getUpdatedOn();

    void setUpdatedOn(Calendar updateTime);

    /**
     * 修改者ip
     */
    String getUpdatedIp();

    void setUpdatedIp(String ip);

    /**
     * 更新时所在的服务器
     * 
     * @return
     */
    String getUpdatedServer();

    void setUpdatedServer(String server);
}
