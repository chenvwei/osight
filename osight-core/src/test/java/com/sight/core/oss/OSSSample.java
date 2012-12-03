/*
 * Created on 2012-12-3
 */
package com.sight.core.oss;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import com.aliyun.openservices.ClientConfiguration;
import com.aliyun.openservices.ClientException;
import com.aliyun.openservices.oss.OSSClient;
import com.aliyun.openservices.oss.OSSException;
import com.aliyun.openservices.oss.model.CannedAccessControlList;
import com.aliyun.openservices.oss.model.GetObjectRequest;
import com.aliyun.openservices.oss.model.OSSObjectSummary;
import com.aliyun.openservices.oss.model.ObjectListing;
import com.aliyun.openservices.oss.model.ObjectMetadata;

/**
 * @author chenw <a href="mailto:chenw@chsi.com.cn">chen wei</a>
 * @version $Id$
 */
public class OSSSample {
    private static final String ACCESS_ID = "0Gf5040A0WisF6dc";
    private static final String ACCESS_KEY = "tXlVh4aELDGbxaUXiMDXC0NX7FM25Z";
    private static final String OSS_ENDPOINT = "http://oss.aliyuncs.com/";

    public static void main(String[] args) throws Exception {
        String bucketName = "osight-test";
        String key = "qq1.jpeg";

        String uploadFilePath = "F:/develop/demo/aliyun/qq.jpeg";
        String downloadFilePath = "F:/develop/demo/aliyun/qq1.jpeg";

        // 可以使用ClientConfiguration对象设置代理服务器、最大重试次数等参数。
        ClientConfiguration config = new ClientConfiguration();
        OSSClient client = new OSSClient(OSS_ENDPOINT, ACCESS_ID, ACCESS_KEY, config);

        ensureBucket(client, bucketName);

        try {
            setBucketPublicReadable(client, bucketName);

            System.out.println("正在上传...");
            uploadFile(client, bucketName, key, uploadFilePath);

            System.out.println("正在下载...");
            downloadFile(client, bucketName, key, downloadFilePath);
        } finally {
            //deleteBucket(client, bucketName);
        }
    }

    // 如果Bucket不存在，则创建它。
    private static void ensureBucket(OSSClient client, String bucketName) throws OSSException, ClientException {

        if (client.isBucketExist(bucketName)) {
            return;
        }

        // 创建bucket
        client.createBucket(bucketName);
    }

    // 删除一个Bucket和其中的Objects
    private static void deleteBucket(OSSClient client, String bucketName) throws OSSException, ClientException {

        ObjectListing ObjectListing = client.listObjects(bucketName);
        List<OSSObjectSummary> listDeletes = ObjectListing.getObjectSummaries();
        for (int i = 0; i < listDeletes.size(); i++) {
            String objectName = listDeletes.get(i).getKey();
            // 如果不为空，先删除bucket下的文件
            client.deleteObject(bucketName, objectName);
        }
        client.deleteBucket(bucketName);
    }

    // 把Bucket设置为所有人可读
    private static void setBucketPublicReadable(OSSClient client, String bucketName) throws OSSException, ClientException {
        // 创建bucket
        client.createBucket(bucketName);

        // 设置bucket的访问权限，public-read-write权限
        client.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
    }

    // 上传文件
    private static void uploadFile(OSSClient client, String bucketName, String key, String filename) throws OSSException,
            ClientException, FileNotFoundException {
        File file = new File(filename);

        ObjectMetadata objectMeta = new ObjectMetadata();
        objectMeta.setContentLength(file.length());
        // 可以在metadata中标记文件类型
        objectMeta.setContentType("image/jpeg");

        InputStream input = new FileInputStream(file);
        client.putObject(bucketName, key, input, objectMeta);
    }

    // 下载文件
    private static void downloadFile(OSSClient client, String bucketName, String key, String filename) throws OSSException,
            ClientException {
        client.getObject(new GetObjectRequest(bucketName, key), new File(filename));
    }
}
