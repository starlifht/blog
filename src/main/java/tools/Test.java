package tools;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public static void main(String[] args){
        try {
            List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("username","star");
            map.put("age","324");
            map.put("date","198-02-34");
            list1.add(map);
            Map<String,Object> map2=new HashMap<String, Object>();
            map2.put("username","stargggg");
            map2.put("age","32777774");
            map2.put("date","19899999-02-34");
            list1.add(map2);
            Map<String,Object> map3=new HashMap<String, Object>();
            map3.put("userlist",list1);
            new Test().createDoc(map3,"/home/star/cc.doc");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
//        try {
//            FileInputStream fileInputStream=new FileInputStream("/home/star/test");
//            FileChannel fileChannel=fileInputStream.getChannel();
//            ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
//           int a= fileChannel.read(byteBuffer,43);
//            System.out.println(a);
//            if (a!=-1){
//                byteBuffer.flip();
//                while (byteBuffer.hasRemaining()){
//                    System.out.print((char)byteBuffer.get());
//                }
//            }
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }
}
