package com.ecpss.httpclient;

import com.ecpss.utils.HttpUtils;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * Created by xc on 2019/6/20.
 */
public class HttpClientDemo {
    
    /*
      请求发送到微信的url
     */
    @Test
    public void aa(){
        Map<String, Object> map = HttpUtils.doGet("https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code");
        System.out.println("map"+map);
        
    }
    
    /**
     * 连接超时和读取超时
     * HttpClient连接时间
 
     所谓连接的时候 是HttpClient发送请求的地方开始到连接上目标url主机地址的时间，理论上是距离越短越快，
 
     线路越通畅越快，但是由于路由复杂交错，往往连接上的时间都不固定，运气不好连不上，HttpClient的默认连接时间，据我测试，
 
     默认是1分钟，假如超过1分钟 过一会继续尝试连接，这样会有一个问题 假如遇到一个url老是连不上，会影响其他线程的线程进去，说难听点，
 
     就是蹲着茅坑不拉屎。所以我们有必要进行特殊设置，比如设置10秒钟 假如10秒钟没有连接上 我们就报错，这样我们就可以进行业务上的处理，
 
     比如我们业务上控制 过会再连接试试看。并且这个特殊url写到log4j日志里去。方便管理员查看。
     */
    /**
     * HttpClient读取时间
 
     所谓读取的时间 是HttpClient已经连接到了目标服务器，然后进行内容数据的获取，一般情况 读取数据都是很快速的，
 
     但是假如读取的数据量大，或者是目标服务器本身的问题（比如读取数据库速度慢，并发量大等等..）也会影响读取时间。
 
     同上，我们还是需要来特殊设置下，比如设置10秒钟 假如10秒钟还没读取完，就报错，同上，我们可以业务上处理。
 
 
     */
    @Test
    public void timeout() throws Exception{
        CloseableHttpClient httpClient=HttpClients.createDefault(); // 创建httpClient实例
        HttpGet httpGet=new HttpGet("http://central.maven.org/maven2/"); // 创建httpget实例
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(10000) //连接十秒钟
                .setSocketTimeout(100)// 读取超时 1000 ms可以读完
                .build();
        httpGet.setConfig(config);
        CloseableHttpResponse response=httpClient.execute(httpGet); // 执行http get请求
        HttpEntity entity=response.getEntity(); // 获取返回实体
        System.out.println("网页内容："+EntityUtils.toString(entity, "utf-8")); // 获取网页内容
        response.close(); // response关闭
        httpClient.close(); // httpClient关闭
//     结果   java.net.SocketTimeoutException: Read timed out
    }
    
    public static void main(String[] args) {
        CloseableHttpClient httpClient = HttpClients.createDefault();//创建httpclient客户端
        HttpGet httpGet = new HttpGet("https://www.baidu.com");//创建httpget实例
        /**
         * User Agent中文名为用户代理，是Http协议中的一部分，属于头域的组成部分
         * 是一种向访问网站提供你所使用的浏览器类型及版本、操作系统及版本、浏览器内核、等信息的标识。
         * HttpClient设置请求头消息User-Agent模拟浏览器
         */
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0"); // 设置请求头消息User-Agent
        CloseableHttpResponse response = null; //对象为空，jvm还没有分配空间 ，内存中不存在
        try {
            response = httpClient.execute(httpGet);//执行http get请求
        } catch (IOException e) { //io异常
            e.printStackTrace();
        }
        
        HttpEntity entity = response.getEntity();//返回实体
        try {
//            HttpClient获取响应内容类型Content-Type
            System.out.println("Content-Type:" + entity.getContentType().getValue());
//            HttpClient获取响应状态Status
            System.out.println("Status:" + response.getStatusLine().getStatusCode());
            System.out.println("网页内容：" + EntityUtils.toString(entity, "utf-8"));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            response.close();
            httpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    @Test
    public void t() {
        String s = null;
        System.out.println(s);
        
    }
    
    @Test
    public void t1() throws Exception {//请求图片--保存
        CloseableHttpClient httpclient = HttpClients.createDefault(); // 创建httpclient实例
        HttpGet httpget = new HttpGet("https://www.baidu.com/img/bd_logo1.png?where=super"); // 创建httpget实例
        
        CloseableHttpResponse response = httpclient.execute(httpget); // 执行get请求
        HttpEntity entity = response.getEntity(); // 获取返回实体
        if (entity != null) {
            System.out.println(entity.getContentType().getValue());
            InputStream stream = entity.getContent();
//            FileOutputStream outputStream=new FileOutputStream("a.png");
//            byte b[]=new byte[1024];
//            int len=0;
//            while ((len=stream.read(b))!=-1){
//                outputStream.write(b,0,len);
//            }
            FileUtils.copyToFile(stream, new File("b.png"));
        }
        
    }
    
    /**
     * 代理，也称网络代理，是一种特殊的网络服务，允许一个网络终端（一般为客户端）通过这个服务与另一个网络终端（一般为服务器）进行非直接的连接。使用代理IP有利于保障网络终端的隐私或安全，防止攻击。
     * <p>
     * 　　代理IP从隐藏级别上区分，可分为三种，即透明代理、普通代理和高匿名代理。三者区别如下：
     * <p>
     * 　　一、透明代理：服务器知道你使用了代理IP，而且知道你的真实IP；
     * <p>
     * 　　二、普通代理：服务器知道你使用了代理IP，但不知道你的真实IP；
     * <p>
     * 　　三、高匿名代理：服务器不知道你使用了代理IP，也不知道你的真实IP。
     * <p>
     * 神么情况下用到代理
     * 比如你要抓取一个网站数据，该网站有100万条内容，他们做了IP限制，每个IP每小时只能抓1000条，
     * 如果单个IP去抓因为受限，需要40天左右才能采集完，如果用了代理IP，不停的切换IP，
     * 就可以突破每小时1000条的频率限制，从而提高效率。其他想切换IP或者隐藏身份的场景也会用到代理IP，比如SEO等。
     */
    public void t2() throws Exception{
        CloseableHttpClient httpClient=HttpClients.createDefault(); // 创建httpClient实例
        HttpGet httpGet=new HttpGet("https://www.taobao.com/"); // 创建httpget实例
        HttpHost proxy=new HttpHost("116.226.217.54", 9999);
        RequestConfig requestConfig=RequestConfig.custom().setProxy(proxy).build();
        httpGet.setConfig(requestConfig);
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0");
        CloseableHttpResponse response=httpClient.execute(httpGet); // 执行http get请求
        HttpEntity entity=response.getEntity(); // 获取返回实体
        System.out.println("网页内容："+EntityUtils.toString(entity, "utf-8")); // 获取网页内容
        response.close(); // response关闭
        httpClient.close(); // httpClient关闭
    
    }
  
}
