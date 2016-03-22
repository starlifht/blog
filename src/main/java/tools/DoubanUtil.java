package tools;

import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;


/**
 * Created by star on 16-3-2.
 */
public class DoubanUtil {
    private  static final  String DOUBAN_SEARCH="https://api.douban.com/v2/movie/search";
    private static final  String DOUBAN_MOVIE="https://api.douban.com/v2/movie/subject/";

    public static  HashMap<String,Object> getMovieInfo(String key){
        int id = 0;
        String info=null;
        String response=HttpClientUtil.get(DOUBAN_SEARCH+"?q="+key);// 基于关键字搜索电影，获取id
        if (response!=null&&response.indexOf("{")!=-1){
            JSONObject jsonObject=JSONObject.fromObject(response);
            if (jsonObject!=null&&jsonObject.containsKey("subjects")){
                id= jsonObject.getJSONArray("subjects").getJSONObject(0).getInt("id");
            }

        }

        if (id==0){
            return null;
        }
        HashMap<String,Object> hashMap=new HashMap<String, Object>();

        info=HttpClientUtil.get(DOUBAN_MOVIE+id); //获取电影的详细信息
        if (info!=null&&info.indexOf("{")!=-1){
            JSONObject jsonObject=JSONObject.fromObject(info);

            if (jsonObject!=null&&jsonObject.containsKey("rating")){
                hashMap.put("rating",jsonObject.getJSONObject("rating").getDouble("average"));
                hashMap.put("url",jsonObject.getString("alt"));
                hashMap.put("countries",jsonObject.getString("countries").replaceAll("[\\[\\]\"]",""));
                hashMap.put("genres",jsonObject.getString("genres").replaceAll("[\\[\\]\"]",""));
                hashMap.put("aka",jsonObject.getString("aka").replaceAll("[\\[\\]\"]",""));
                hashMap.put("images",jsonObject.getJSONObject("images"));
                hashMap.put("title",jsonObject.getString("title"));
                hashMap.put("year",jsonObject.getInt("year"));
                hashMap.put("id",id);
            }


        }
        return hashMap;
    }

    public static void main(String[] args){
//           JSONObject jsonObject=new JSONObject();
//        ArrayList arrayList=new ArrayList();
//        arrayList.add("册四");
//        arrayList.add("册5");
//        jsonObject.put("star",0);
//        jsonObject.put("list",arrayList);

     //   System.out.println(jsonObject.getString("list"));
        String s="[\"sdfsd\",sdfsd]";
        System.out.println(s.replaceAll("[\\[\\]\"]",""));
    }
}
