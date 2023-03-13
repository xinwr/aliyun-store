package com.xunpou.aliyun.util;

import org.apache.commons.io.IOUtils;
import okhttp3.*;
import javax.net.ssl.HttpsURLConnection;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * 阿里云工具类
 */
public class AliUtil {
    private static final byte[] hexChar = "0123456789abcdef".getBytes(StandardCharsets.UTF_8);
    private static OkHttpClient  okHttpClient;
    private static final Object okHttpClientLock = new Object();
    public static String byteToHex(byte[] content){
        byte[] hexByte = new byte[content.length*2];
        int index=0;
        for (int i=0;i<content.length;i++,index+=2){
            hexByte[index]=  hexChar[((content[i]&0xf0)>>4)];
            hexByte[index+1]= hexChar[(content[i]&0x0f)];
        }

        return new String(hexByte);
    }

    public static String sha1(String content) throws Exception{
        return sha1(content.getBytes(StandardCharsets.UTF_8));
    }

    public static String sha1(byte[] content) throws Exception{
        MessageDigest messageDigest = MessageDigest.getInstance("sha1");
        byte[] res = messageDigest.digest(content);
        return byteToHex(res);
    }

    public static String sha1(byte[] content,int offset,int length) throws Exception{
        MessageDigest messageDigest = MessageDigest.getInstance("sha1");
        messageDigest.update(content,offset,length);
        byte[] res = messageDigest.digest();
        return byteToHex(res);
    }

    public static String md5(byte[] content) throws Exception{
        MessageDigest messageDigest = MessageDigest.getInstance("md5");
        byte[] res = messageDigest.digest(content);
        return byteToHex(res);
    }

    public static byte[] getByRange(String downloadUrl,long start,long end) throws Exception{
        HttpURLConnection connection = (HttpURLConnection) new URL(downloadUrl).openConnection();
        connection.addRequestProperty("Referer","https://www.aliyundrive.com/");
        connection.addRequestProperty("Range","bytes="+start+"-"+end);
        return IOUtils.toByteArray(connection.getInputStream());
    }

    public static byte[] getByRangeInOkHttp(String downloadUrl,long start,long end) throws Exception{
        if(okHttpClient==null){
            synchronized (okHttpClientLock){
                if(okHttpClient==null){
                    okHttpClient = new  OkHttpClient.Builder().build();
                }
            }
        }
        Request.Builder request = new Request.Builder();
        request.url(downloadUrl);
        request.addHeader("Referer","https://www.aliyundrive.com/");
        request.addHeader("Range","bytes="+start+"-"+end);
        Response response = okHttpClient.newCall(request.build()).execute();
        return response.body().bytes();
    }


}
