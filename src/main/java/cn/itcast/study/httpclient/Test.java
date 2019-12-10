package cn.itcast.study.httpclient;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.http.converter.json.GsonBuilderUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Test {
    public static void main(String[] args) throws URISyntaxException, UnsupportedEncodingException {


        // 创建httpclient对象 设置url访问地址


        final CloseableHttpClient httpClient = HttpClients.createDefault();
        //使用httpclient发送请求 获取response



//        final HttpPost httpPost = new HttpPost("http://yun.itheima.com/search");


        HttpPost httpPost = new HttpPost("http://127.0.0.1:8080/testPost");


        httpPost.setHeader("Content-Type", "application/json");


        final HashMap<Object, Object> map = new HashMap<>();


        map.put("name","张三");
        map.put("age","22");
        map.put("sex","男");
        map.put("height","180");
        //设置请求体

        final String body = new Gson().toJson(map);

        System.out.println(body);
        final StringEntity stringEntity = new StringEntity(body);
        stringEntity.setContentType("text/json");
        httpPost.setEntity(stringEntity);
        //获取返回信息

        //StringEntity se = new StringEntity(j.toString());
        //	se.setContentType("text/json");

        //解析响应数据
        System.out.println("发送请求的信息"+httpPost);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == 200) {
                final HttpEntity entity = response.getEntity();

                final String content = EntityUtils.toString(entity, "utf-8");
                System.out.println(content);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}
