package cn.itcast.study.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

import static javax.swing.UIManager.get;

public class HttpClientPoolTest {

    public static void main(String[] args) {
        //创建连接池管理器


        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();

        //设置最大连接数量
        cm.setMaxTotal(100);
        //设置每个主机的最大连接数
        cm.setDefaultMaxPerRoute(10);


        //使用连接池发送请求

        doGet(cm);
        doGet(cm);
//        doPost(cm);























    }



    private static void doPost(PoolingHttpClientConnectionManager cm) {
        //不是每次创建新的httpclient对象 而是从连接池中获取httpclient对象

        final CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();


        final HttpPost httpPost = new HttpPost("http://www.itcast.com");

          CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);


            if (response.getStatusLine().getStatusCode()==200) {
                final HttpEntity entity = response.getEntity();
                final String content = EntityUtils.toString(entity, "utf-8");
                System.out.println(content.length());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response!=null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    private static void doGet(PoolingHttpClientConnectionManager cm) {

        //不是每次创建新的httpclient对象 而是从连接池中获取httpclient对象

        final CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();


        final HttpGet httpGet = new HttpGet("http://www.itcast.com");

        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);

            if (response.getStatusLine().getStatusCode()==200) {
                final HttpEntity entity = response.getEntity();
                final String content = EntityUtils.toString(entity, "utf-8");
                System.out.println(content.length());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response!=null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
