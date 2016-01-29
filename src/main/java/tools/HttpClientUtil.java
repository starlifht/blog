package tools;
/*
STAR
 */

import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class HttpClientUtil {


    /**
     * 发送Get请求
     * @param url
     * @param params
     * @return
     */
    public static String get(String url, List<NameValuePair> params){
        CloseableHttpClient client =  null;
        CloseableHttpResponse httpresponse=null;
        String body = null;
        try {
            client =  HttpClients.createDefault();
            // Get请求
            HttpGet httpget = new HttpGet(url);
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(3000).setConnectTimeout(3000).build();//设置请求和传输超时时间
            httpget.setConfig(requestConfig);
            // 设置参数
            if (params!=null) {
                String str = EntityUtils.toString(new UrlEncodedFormEntity(params));
                httpget.setURI(new URI(httpget.getURI().toString() + "?" + str));
            }
            // 发送请求
             httpresponse = client.execute(httpget);

            // 获取返回数据
            HttpEntity entity = httpresponse.getEntity();
            if(entity!=null) {
                body = EntityUtils.toString(entity);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }finally {
            try {
                httpresponse.close();
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return body;
    }
    public static String post(String url, List<NameValuePair> params) {

        CloseableHttpClient client =  null;
        CloseableHttpResponse httpresponse=null;
        String body = null;
        try {
           // CookieStore cookieStore=new BasicCookieStore();
//            BasicClientCookie username=new BasicClientCookie("username","starlifht");
//            username.setPath("/");
//            username.setDomain(".xixihd.com");
//            BasicClientCookie uid=new BasicClientCookie("uid","5893");
//            uid.setPath("/");
//            uid.setDomain(".xixihd.com");
//            BasicClientCookie token=new BasicClientCookie("token","3b0b038326d50b90cdc0240f0ba99320");
//            token.setPath("/");
//            token.setDomain(".xixihd.com");
//            cookieStore.addCookie(username);
//            cookieStore.addCookie(uid);
//            cookieStore.addCookie(token);
            client =  HttpClients.createDefault();

            HttpPost httpget = new HttpPost(url);
            // 设置参数
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000).build();//设置请求和传输超时时间
            httpget.setConfig(requestConfig);
            String str = EntityUtils.toString(new UrlEncodedFormEntity(params));
            httpget.setURI(new URI(httpget.getURI().toString() + "?" + str));
            // 发送请求
             httpresponse = client.execute(httpget);
            // 获取返回数据
            HttpEntity entity = httpresponse.getEntity();

            if (entity != null) {
                body = EntityUtils.toString(entity);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }finally {
            try {
                httpresponse.close();
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return body;
    }
    public static String post(String url, JSONObject jsonObject) {

        CloseableHttpClient client =  null;
        CloseableHttpResponse httpresponse=null;
        String body = null;
        try {
            client =  HttpClients.createDefault();
            HttpPost httpget = new HttpPost(url);
            // 设置参数
            httpget.addHeader("content-type", "application/json");
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000).build();//设置请求和传输超时时间
            httpget.setConfig(requestConfig);
            httpget.setEntity(new StringEntity(jsonObject.toString(),"UTF-8"));
            // 发送请求
            httpresponse = client.execute(httpget);
            // 获取返回数据
            HttpEntity entity = httpresponse.getEntity();

            if (entity != null) {
                body = EntityUtils.toString(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                httpresponse.close();
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return body;
    }
    /**
     * 发送 Post请求
     * @param url
     * @param reqXml
     * @return
     */
//    public static String postXML(String url, String reqXml) {
//        String body = null;
//        CloseableHttpClient httpClient =  HttpClients.createDefault();
//        try {
//            //设置客户端编码
//            if (httpClient == null) {
//                // Create HttpClient Object
//                httpClient = new DefaultHttpClient();
//            }
//            httpClient.getParams().setParameter("http.protocol.content-charset",HTTP.UTF_8);
//            httpClient.getParams().setParameter(HTTP.CONTENT_ENCODING, HTTP.UTF_8);
//            httpClient.getParams().setParameter(HTTP.CHARSET_PARAM, HTTP.UTF_8);
//            httpClient.getParams().setParameter(HTTP.DEFAULT_PROTOCOL_CHARSET,HTTP.UTF_8);
//            // Post请求
//            HttpPost httppost = new HttpPost(url);
//            //设置post编码
//            httppost.getParams().setParameter("http.protocol.content-charset",HTTP.UTF_8);
//            httppost.getParams().setParameter(HTTP.CONTENT_ENCODING, HTTP.UTF_8);
//            httppost.getParams().setParameter(HTTP.CHARSET_PARAM, HTTP.UTF_8);
//            httppost.getParams().setParameter(HTTP.DEFAULT_PROTOCOL_CHARSET, HTTP.UTF_8);
//
//            // 设置参数
//            List<NameValuePair> params = new ArrayList<NameValuePair>();
//            params.add(new BasicNameValuePair("$xmldata", reqXml));
//            httppost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
////			StringEntity entity1 = new StringEntity(getUTF8XMLString(reqXml), "UTF-8");
////			entity1.setContentType("text/xml;charset=UTF-8");
////			entity1.setContentEncoding("UTF-8");
////			httppost.setEntity(entity1);
//            //设置报文头
//            httppost.setHeader("Content-Type", "text/xml;charset=UTF-8");
//            // 发送请求
//            HttpResponse httpresponse = httpClient.execute(httppost);
//            // 获取返回数据
//            HttpEntity entity = httpresponse.getEntity();
//            body = EntityUtils.toString(entity);
//            if (entity != null) {
//                entity.consumeContent();
//            }
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return body;
//    }
    /**
     * 根据样式格式化时间
     * "yyyyMMdd"
     * "yyyyMMddHHmmss"
     * "yyyyMMddHHmmssssssss"
     * @param dateFormat
     * @return
     */
    public static String getnowDate(String dateFormat)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(new Date());
    }
    public static void main (String[] args){
        List<NameValuePair> list=new ArrayList<NameValuePair>();

//        list.add(new BasicNameValuePair("dowid","0"));
//       list.add(new BasicNameValuePair("code","true"));
       list.add(new BasicNameValuePair("echostr","b3a88de970c130c59a789b61ba6fc0c4"));
       list.add(new BasicNameValuePair("reurl","%2F"));
       list.add(new BasicNameValuePair("username","starlifht"));
       list.add(new BasicNameValuePair("password","787870724"));
       list.add(new BasicNameValuePair("cookietime","30"));
       list.add(new BasicNameValuePair("type",""));
       list.add(new BasicNameValuePair("user","login"));
//        post("http://172.27.1.31:8080/jm/runtest",list);
        System.out.println(post("http://www.xixihd.com/", list));
    }

}

