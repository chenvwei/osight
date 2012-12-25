/*
* Created on 2012-12-25
*/
package com.osight.history.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

/**
 * @author chenw
 * @version $Id$
 */
@Retention(RUNTIME)
public @interface HistoryProp {
    
    /**
     * 是否对insert事件作出记录，默认为false，因为这样会导致日志表过大，而且即使不记录insert事件也能找到完全的历史记录
     * @return
     */
    boolean historyInsert() default false;
    
}
