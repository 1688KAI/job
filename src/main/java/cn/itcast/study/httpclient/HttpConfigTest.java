package cn.itcast.study.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;

public class HttpConfigTest {
    public static void main(String[] args) throws URISyntaxException {



        //创建httpclient对象

        final CloseableHttpClient httpClient = HttpClients.createDefault();


    // 创建httpGet对象 设置url访问地址
        final HttpGet httpGet = new HttpGet("http://www.itcast.cn");

        //配置请求信息
        RequestConfig config=RequestConfig.custom().setConnectTimeout(10000)//创建 连接的最长时间 单位毫秒
                .setConnectionRequestTimeout(500) //设置获取连接的最长时间 单位毫秒
                .setSocketTimeout(10*1000).build();//设置数据传输的最长时间 单位毫秒



        //给请求 设置请求信息
        httpGet.setConfig(config);

        //解析响应数据
        //使用httpclient发送请求 获取response

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
