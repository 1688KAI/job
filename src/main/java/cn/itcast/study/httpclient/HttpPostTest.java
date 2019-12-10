package cn.itcast.study.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class HttpPostTest {
    public static void main(String[] args) throws URISyntaxException, UnsupportedEncodingException {


        // 创建httpclient对象 设置url访问地址


        final CloseableHttpClient httpClient = HttpClients.createDefault();
        //使用httpclient发送请求 获取response



//        final HttpPost httpPost = new HttpPost("http://yun.itheima.com/search");


        HttpPost httpPost = new HttpPost("http://127.0.0.1:8080/testPost");

        //声明List集合 封装表单中的参数

        List<NameValuePair> parems=new ArrayList<NameValuePair>();


        parems.add(new BasicNameValuePair("keys","java"));
        //创建表单中的entity对象

        UrlEncodedFormEntity formEntity=new UrlEncodedFormEntity(parems,"utf-8");
        //设置表单的entity对象到请求中

        httpPost.setEntity(formEntity);

        //解析响应数据
        System.out.println("发送请求的信息"+httpPost);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == 200) {
                final HttpEntity entity = response.getEntity();

                final String content = EntityUtils.toString(entity, "utf-8");
                System.out.println(content.length());
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
