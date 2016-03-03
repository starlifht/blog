package tools;

import net.sf.json.JSONObject;

import java.util.HashMap;


/**
 * Created by star on 16-3-2.
 */
public class DoubanUtil {
    private  static final  String DOUBAN_SEARCH="https://api.douban.com/v2/movie/search";
    private static final  String DOUBAN_MOVIE="https://api.douban.com/v2/movie/subject/";

//    public static int getMovieID(String key){
//        int id = 0;
//
//        String response=HttpClientUtil.get(DOUBAN_SEARCH+"?q="+key);
//        if (response!=null){
//            JSONObject jsonObject=JSONObject.fromObject(response);
//           id= jsonObject.getJSONArray("subjects").getJSONObject(0).getInt("id");
//        }
//        return id;
//    }
    public static  JSONObject getMovieInfo(String key){
        int id = 0;
        String info=null;
        String response=HttpClientUtil.get(DOUBAN_SEARCH+"?q="+key);
        if (response!=null){
            JSONObject jsonObject=JSONObject.fromObject(response);
            if (jsonObject!=null&&jsonObject.containsKey("subjects")){
                id= jsonObject.getJSONArray("subjects").getJSONObject(0).getInt("id");
            }

        }

        if (id==0){
            return null;
        }
        JSONObject jsinInfo=new JSONObject();

        info=HttpClientUtil.get(DOUBAN_MOVIE+id);
        if (info!=null){
            JSONObject jsonObject=JSONObject.fromObject(info);

            if (jsonObject!=null&&jsonObject.containsKey("rating")){
                jsinInfo.put("rating",jsonObject.getJSONObject("rating").getDouble("average"));
                jsinInfo.put("url",jsonObject.getString("alt"));
                jsinInfo.put("countries",jsonObject.getJSONArray("countries"));
                jsinInfo.put("genres",jsonObject.getJSONArray("genres"));
                jsinInfo.put("images",jsonObject.getJSONObject("images"));
            }


        }
        return jsinInfo;
    }

    public static void main(String[] args){

        System.out.println(getMovieInfo("love"));

    }
}
