package cn.itcast.study.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class CrawlerFrist {

    public static void main(String[] args) throws IOException {

        final CloseableHttpClient httpClient = HttpClients.createDefault();


        final HttpGet httpGet = new HttpGet("http://www.baidu.com");

        final CloseableHttpResponse response = httpClient.execute(httpGet);


        if (response.getStatusLine().getStatusCode()==200) {

            final HttpEntity entity = response.getEntity();
            final String content = EntityUtils.toString(entity, "utf-8");
            System.out.println(content);
        }


    }
}
