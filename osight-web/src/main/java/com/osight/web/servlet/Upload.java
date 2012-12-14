/*
 * Created on 2012-11-23
 */
package com.osight.web.servlet;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.osight.core.service.AlbumService;
import com.osight.core.service.AlbumServiceFactory;
import com.osight.framework.util.UUIDUtil;

/**
 * @author chenw
 * @version $Id$
 */
public class Upload extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Logger log = LoggerFactory.getLogger(getClass());

    private AlbumService albumService = AlbumServiceFactory.getAlbumService();

    public static void main(String[] args) {
        String path = "/mnt/web/static/images/album/2012/12/14/fdsafdsf.jpg";
        System.out.println(path.substring(path.indexOf("images")));
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("upload start------");
        request.setCharacterEncoding("UTF-8");
        StringBuffer savePath = new StringBuffer("/mnt/web/static/images/album/");
        Calendar c = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd/");
        String date = df.format(c.getTime());
        savePath.append(date);
        File dir = new File(savePath.toString());
        if (!dir.exists()) {
            dir.mkdirs();
        }
        DiskFileItemFactory fac = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(fac);
        upload.setHeaderEncoding("UTF-8");
        List<FileItem> list = null;
        try {
            list = upload.parseRequest(request);
        } catch (FileUploadException e) {
            log.error(e.getMessage(), e);
            return;
        }
        log.info("file size:{}", list.size());
        for (FileItem it : list) {
            if (!it.isFormField()) {
                String name = it.getName();
                long size = it.getSize();
                String type = it.getContentType();
                log.info("name:{},size:{},type:{}", name, size, type);
                if (StringUtils.isBlank(name)) {
                    continue;
                }
                String extName = null;
                if (name.lastIndexOf(".") >= 0) {
                    extName = name.substring(name.lastIndexOf("."));
                }
                File file = null;
                do {
                    name = UUIDUtil.getRandomUUID();
                    file = new File(savePath + name + extName);
                } while (file.exists());
                File saveFile = new File(savePath + name + extName);
                log.info("path:{}", saveFile.getAbsolutePath());
                try {
                    it.write(saveFile);
                    albumService.newPhoto(1, saveFile.getAbsolutePath(), "none");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        response.getWriter().write("1");
        log.info("upload end------");
    }
}
