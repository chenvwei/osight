/*
 * Created on 2012-11-22
 */
package com.osight.framework.exception;

/**
 * 参数异常
 * 
 * @author chenw
 * @version $Id$
 */
public class ArgumentException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ArgumentException() {
        super();
    }

    public ArgumentException(String arg0) {
        super(arg0);
    }

    public ArgumentException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public ArgumentException(Throwable arg0) {
        super(arg0);
    }
}
