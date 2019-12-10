package cn.itcast.study.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;

public class HttpGetTest {
    public static void main(String[] args) throws URISyntaxException {


        // 创建httpclient对象 设置url访问地址


        final CloseableHttpClient httpClient = HttpClients.createDefault();
        //使用httpclient发送请求 获取response


        //创建URIBuilder  http://itcast.com/search?keys=Java
        final URIBuilder uriBuilder = new URIBuilder("http://yun.itheima.com/search");

        uriBuilder.addParameter("keys","java");

        final HttpGet httpGet = new HttpGet(uriBuilder.build());
        //解析响应数据


        System.out.println("发送请求的信息"+httpGet);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
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
