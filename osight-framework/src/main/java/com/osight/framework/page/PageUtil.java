/*
 * Created on 2012-12-26
 */
package com.osight.framework.page;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chenw
 * @version $Id$
 */
public class PageUtil {
    private static final Logger log = LoggerFactory.getLogger(PageUtil.class);

    public static <T> Page<T> getPage(Iterator<T> itr, long start, int pageCount, long totalCount) {
        List<T> list = new ArrayList<T>((int) (pageCount > totalCount ? totalCount : pageCount));
        while (itr.hasNext()) {
            T pageObject = itr.next();
            if (null != pageObject) {
                list.add(pageObject);
            } else {
                log.error("Null object founded!");
            }
        }
        if (0 == list.size() || 0 == pageCount) {
            return new Page<T>(new ArrayList<T>(0), 0, 0, pageCount, false);
        } else {
            boolean hasNext = (start + pageCount) < totalCount ? true : false;
            return new Page<T>(list, (int) start, (int) totalCount, pageCount, hasNext);
        }
    }
}
