package cn.itcast.util;


import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.UUID;

@Component
public class HttpClientUtil {


    private PoolingHttpClientConnectionManager mc;
    private RequestConfig config;

    HttpClientUtil() {
        this.mc = new PoolingHttpClientConnectionManager();
        this.mc.setMaxTotal(100);
        this.mc.setDefaultMaxPerRoute(10);

    }


    HttpHost proxy = new HttpHost("121.40.162.239", 808, "https");


    //添加代理，IP为本地IP 8888就是fillder的端口   	9999
    private RequestConfig getConfig() {
        this.config = RequestConfig.custom()
                .setConnectTimeout(10000)
                .setConnectionRequestTimeout(500)
                .setSocketTimeout(10000)
//                .setProxy(proxy)
                .build();
        return this.config;
    }


    /**
     * 根据请求地址下载 页面数据
     *
     * @param url
     * @return
     */
    public String doGetHtml(String url) {
        final CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(mc).build();

        //设置代理服务器的ip地址和端口


        HttpGet httpGet = new HttpGet(url);

        //代理

        httpGet.setConfig(config);
        httpGet.setConfig(getConfig());
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                if (response.getEntity() != null) {
                    String content = EntityUtils.toString(response.getEntity(), "utf-8");
                    return content;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        return "";
    }


    public String doGetImage(String url) {
        final CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(mc).build();

        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(getConfig());

        httpGet.setHeader("Content-type", "application/x-www-form-urlencoded");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36");
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                if (response.getEntity() != null) {
                    HttpEntity entity = response.getEntity();
                    //获取图片的后缀
                    String extName = url.substring(url.lastIndexOf("."));
                    //创建文件名称
                    final String picName = UUID.randomUUID().toString() + extName;


                    final File file = new File("D:\\image\\");

                    if (!file.exists()) {
                        file.mkdir();
                    }
                    OutputStream outputStream = new FileOutputStream(new File(file + picName));
                    entity.writeTo(outputStream);
                    return picName;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    public String doGetImage(String url, String title,String path) {
        final CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(mc).build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(getConfig());

        httpGet.setHeader("Content-type", "application/x-www-form-urlencoded");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36");
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                if (response.getEntity() != null) {
                    HttpEntity entity = response.getEntity();
                    System.out.println();
                    String suffix = url.substring(url.lastIndexOf("/") + 1);
                    final File file = new File(path+title+"\\"+suffix);

                    InputStream inputStream = entity.getContent();
                    FileUtils.copyInputStreamToFile(inputStream,file);
                    return file.getName();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    public static void main(String[] args) {
        String file = "https://www.leshe.us/wp-content/uploads/2019/11/1573394225-0034.jpg";


        System.out.println(file.substring(file.lastIndexOf("/")+1));
    }
}
