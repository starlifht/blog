package tools;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pojo.FilmInfo;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by star on 16-2-1.
 */
public class Test {
    public void createDoc(Map<String,Object> dataMap, String fileName) throws UnsupportedEncodingException {
        //dataMap 要填入模本的数据文件
        //设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以重servlet，classpath，数据库装载，
        //这里我们的模板是放在template包下面
     Configuration configuration=new Configuration();
//        configuration.setClassForTemplateLoading(this.getClass(), "/home/star/IdeaProjects/blog/src/main/webapp/WEB-INF/view");
//        configuration.se
        Template t=null;
        try {
            //test.ftl为要装载的模板
            t = configuration.getTemplate("cc.ftl");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //输出文档路径及名称
        File outFile = new File(fileName);

        Writer out = null;
        FileOutputStream fos=null;
        try {
            fos = new FileOutputStream(outFile);
            OutputStreamWriter oWriter = new OutputStreamWriter(fos,"UTF-8");
            //这个地方对流的编码不可或缺，使用main（）单独调用时，应该可以，但是如果是web请求导出时导出后word文档就会打不开，并且包XML文件错误。主要是编码格式不正确，无法解析。
            //out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
            out = new BufferedWriter(oWriter);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }

        try {
            t.process(dataMap, out);
            out.close();
            fos.close();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //System.out.println("---------------------------");
    }
    public int kuaipai(int left,int right,int[] x){
        int base=x[left];
        while (left<right){

            while (left<right&&x[right]>base){
                right--;
            }
            x[left]=x[right];
            while (left<right&&x[left]<base){
                left++;
            }
            x[right]=x[left];

        }
        x[left]=base;
        return left;

    }
    public void sort(int left,int right,int[] x){
        if (left<right){
            int base= kuaipai(left,right,x);
            sort(left,base,x);
            sort(base+1,right,x);
        }

    }
    public static void main(String[] args){

               int[] a={2,5,3,9,7,1};
//        for (int i=0;i<a.length;i++){
//
//            for (int j=a.length-1;j>i;j--){
//                if (a[j]<a[j-1]){
//                    int temp=a[j];
//                    a[j]=a[j-1];
//                    a[j-1]=temp;
//                }
//
//            }
//        }
//        new Test().sort(0,a.length-1,a);
//        for (int i =1;i<a.length;i++){
//            int temp=a[i];
//            int j;
//            for (j=i-1;j>=0&&temp<a[j];j--){
//
//                  a[j+1]=a[j];
//
//            }
//            a[j+1]=temp;
//        }
        for (int i=0;i<a.length-1;i++) {
            int index = i;

            for (int j = i + 1; j < a.length; j++) {
                if (a[index] > a[j]) {
                    index = j;
                }
            }
            int temp=a[i];
            a[i]=a[index];
            a[index]=temp;
        }
            for (int b : a) {
                System.out.println(b);
            }
        }}

