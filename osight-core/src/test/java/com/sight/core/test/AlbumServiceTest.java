/*
 * Created on 2012-12-14
 */
package com.sight.core.test;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.junit.Test;

import com.osight.core.pojos.AlbumPhotoData;
import com.osight.core.service.AlbumService;
import com.osight.core.service.AlbumServiceFactory;

/**
 * @author chenw
 * @version $Id$
 */
public class AlbumServiceTest {
    AlbumService albumService = AlbumServiceFactory.getAlbumService();

    @Test
    public void test() {
        System.out.println(111);
    }

    @Test
    public void testNewPhoto() {
        AlbumPhotoData data = albumService.newPhoto(1, "/mnt/web/static/images/album/2012/12/14/fdsafdsf.jpg", "none");
        System.out.println(ToStringBuilder.reflectionToString(data, ToStringStyle.MULTI_LINE_STYLE));
    }
}
