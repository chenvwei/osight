/*
 * Created on 2012-11-23
 */
package com.osight.framework.util;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chenw
 * @version $Id$
 */
public class DigestUtil {
    protected static final String DEFAULT_ALGORITHM = "MD5";
    static Logger log = LoggerFactory.getLogger(DigestUtil.class);

    public static String MD5(String input) {
        byte[] defaultBytes = input.getBytes();
        return MD5(defaultBytes);
    }

    public static String MD5(byte[] input) {
        MessageDigest algorithm = getDigest("MD5");
        algorithm.reset();
        algorithm.update(input);
        byte messageDigest[] = algorithm.digest();
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < messageDigest.length; i++) {
            String hex = Integer.toHexString(0xFF & messageDigest[i]);
            if (hex.length() == 1)
                hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    /**
     * 文件md5
     * 
     * @param in
     * @return
     * @throws IOException
     */
    public static String MD5(InputStream in) throws IOException {
        MessageDigest algorithm = getDigest("MD5");
        algorithm.reset();
        byte[] buffer = new byte[8196];
        int len;
        while ((len = in.read(buffer)) != -1) {
            algorithm.update(buffer, 0, len);// 不可使用algorithm.update(buffer)
        }
        byte messageDigest[] = algorithm.digest();
        StringBuffer hexString = new StringBuffer(messageDigest.length * 2);
        for (int i = 0; i < messageDigest.length; i++) {
            String hex = Integer.toHexString(0xFF & messageDigest[i]);
            if (hex.length() == 1)
                hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static MessageDigest getDigest() {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance(DEFAULT_ALGORITHM);
        } catch (NoSuchAlgorithmException f) {
            log.error("No " + DEFAULT_ALGORITHM + " available");
            digest = null;
        }
        return digest;
    }

    public static MessageDigest getDigest(String algorithm) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance(algorithm);
            return digest;
        } catch (NoSuchAlgorithmException e) {
            log.error("No " + algorithm + " available");
            return null;
        }
    }

    /**
     * 
     * 这个方法，根据加密算法来完成对input数据的加密处理 默认使用md5加密
     * 
     * @param dt
     * @param input
     * @return
     */
    public static String encrypted(DigestMethodType dt, String input) {
        if (dt == null) {
            dt = DigestMethodType.MD5;
        }
        switch (dt) {
        case MD5:
            return MD5(input);
        default:
            return MD5(input);
        }
    }

    public enum DigestMethodType {
        // NONE, // 不加密
        MD5; // MD5加密
    }
}
