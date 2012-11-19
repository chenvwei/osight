/*
 * Created on 2012-11-19
 */
package com.osight.framework.exception;

/**
 * @author chenw <a href="mailto:chenw@chsi.com.cn">chen wei</a>
 * @version $Id$
 */
public class DAOException extends RuntimeException {
    public DAOException() {
        super();
    }

    public DAOException(String arg0) {
        super(arg0);
    }

    public DAOException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public DAOException(Throwable arg0) {
        super(arg0);
    }
}
