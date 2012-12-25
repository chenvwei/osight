/*
* Created on 2012-12-25
*/
package com.osight.history.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author chenw
 * @version $Id$
 */
@Retention(RUNTIME)
@Target({ElementType.FIELD})
public @interface NoHistory {
    boolean isNoInsertVersion() default true;
    boolean isNoUpdateVersion() default true;
    boolean isNoDeleteVersion() default false;
}
