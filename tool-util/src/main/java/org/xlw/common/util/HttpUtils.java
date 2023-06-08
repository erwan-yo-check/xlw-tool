package org.xlw.common.util;

import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Description: HTTP工具
 * Author: erwan_check
 * Email: 1076360205@qq.com
 * Date: 2023/5/31 15:47
 */
public class HttpUtils {

    private static final CloseableHttpClient httpclient = HttpClients.createDefault();

    /**
     * 发送HttpGet请求
     *
     * @param url
     * @return
     */
    public static String sendGet(String url) {

        HttpGet httpget = new HttpGet(url);
        RequestConfig build = RequestConfig.custom().setSocketTimeout(5000)
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(5000).build();
        httpget.setConfig(build);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpget);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        String result = null;
        try {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity);
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * post请求（用于请求json格式的参数）
     *
     * @param url
     * @param params
     * @return
     */
    public static String doPost(String url, String params, String charSet) throws Exception {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httpPost
        HttpPost httpPost = new HttpPost(url);
        //构建超时等配置信息
        RequestConfig config = RequestConfig.custom().setConnectTimeout(60*1000) //连接超时时间
                .setConnectionRequestTimeout(60 *1000) //从连接池中取的连接的最长时间
                .setSocketTimeout(60 *1000) //数据传输的超时时间
                .setStaleConnectionCheckEnabled(true) //提交请求前测试连接是否可用
                .build();
        //设置请求配置时间ddd
        httpPost.setConfig(config);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-Type", "application/json");
        if (StringUtils.isEmpty(charSet)){
            charSet = "UTF-8";
        }
        StringEntity entity = new StringEntity(params, charSet);
        httpPost.setEntity(entity);

        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpPost);
            StatusLine status = response.getStatusLine();
            int state = status.getStatusCode();
            if (state == HttpStatus.SC_OK) {
                HttpEntity responseEntity = response.getEntity();
                String jsonString = EntityUtils.toString(responseEntity, charSet);
                return jsonString;
            }

        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    return "";
                }
            }
            try {
                httpclient.close();
            } catch (IOException e) {
                return "";
            }
        }
        return null;
    }


    /**
     * 发送HttpPost请求，参数为map
     *
     * @param url
     * @param map
     * @return
     */
    public static String sendPost(String url, Map<String, String> map) {
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
        HttpPost httppost = new HttpPost(url);
        httppost.setEntity(entity);
        RequestConfig build = RequestConfig.custom()./*setProxy(new HttpHost("192.168.31.202",8080)).*/setSocketTimeout(5000)
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(5000).build();
        httppost.setConfig(build);


        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httppost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity entity1 = response.getEntity();
        String result = null;
        try {
            result = EntityUtils.toString(entity1);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String sendPost(String url, String jsonBody, Map<String, String> headerMap) {
        HttpPost httppost = new HttpPost(url);
        // 写入json
        StringEntity stringEntity;
        stringEntity = new StringEntity(jsonBody, "utf-8");
        stringEntity.setContentType("application/json");
        httppost.setEntity(stringEntity);
        // 写入header
        for (String key : headerMap.keySet()) {
            httppost.addHeader(key, headerMap.get(key));
        }
        RequestConfig build = RequestConfig.custom()./*setProxy(new HttpHost("192.168.31.202",8080)).*/setSocketTimeout(5000)
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(5000).build();
        httppost.setConfig(build);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httppost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity entity1 = response.getEntity();
        String result = null;
        try {
            result = EntityUtils.toString(entity1);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return result;
    }



    public static String sendPost(String url, Map<String, String> map, Map<String, String> headerMap) {
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        HttpPost httppost = new HttpPost(url);
        for (String key : headerMap.keySet()) {
            httppost.addHeader(key, headerMap.get(key));
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
        httppost.setEntity(entity);
        RequestConfig build = RequestConfig.custom()./*setProxy(new HttpHost("192.168.31.202",8080)).*/setSocketTimeout(5000)
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(5000).build();
        httppost.setConfig(build);


        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httppost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity entity1 = response.getEntity();
        String result = null;
        try {
            result = EntityUtils.toString(entity1);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 传递对象
     *
     * @param url
     * @param map
     * @return
     */
    public static String sendPostObj(String url, String map) {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        HttpResponse res = null;
        HttpPost post = new HttpPost(url);
        StringEntity stringEntity;
        stringEntity = new StringEntity(map, "utf-8");
        stringEntity.setContentType("application/json");
        post.setEntity(stringEntity);
        try {
            res = closeableHttpClient.execute(post);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity entity = res.getEntity();
        String result = null;
        try {
            result = EntityUtils.toString(entity, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 支持header
     *
     * @param url
     * @param mapParam
     * @param headerMap
     * @return
     */
    public static String sendPostObj(String url, String mapParam, Map<String, String> headerMap) {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        HttpResponse res = null;
        HttpPost post = new HttpPost(url);
        StringEntity stringEntity;
        for (String key : headerMap.keySet()) {
            post.addHeader(key, headerMap.get(key));
        }
        stringEntity = new StringEntity(mapParam, "utf-8");
        post.setEntity(stringEntity);
        try {
            res = closeableHttpClient.execute(post);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity entity = res.getEntity();
        String result = null;
        try {
            result = EntityUtils.toString(entity, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 发送不带参数的HttpPost请求
     *
     * @param url
     * @return
     */
    public static String sendPost(String url) {
        HttpPost httppost = new HttpPost(url);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httppost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity entity = response.getEntity();
        String result = null;
        try {
            result = EntityUtils.toString(entity);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}