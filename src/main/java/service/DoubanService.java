package service;

import dao.DouBanInfoMapper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.DouBanInfo;
import tools.HttpClientUtil;

import java.util.Iterator;

/**
 * Created by star on 16-3-24.
 */
@Service
public class DoubanService {
    @Autowired
    private DouBanInfoMapper douBanInfoMapper;

    private   final  String DOUBAN_SEARCH="https://api.douban.com/v2/movie/search";
    private  final  String DOUBAN_MOVIE="https://api.douban.com/v2/movie/subject/";


    public int  getDoubanId(String key){
        int id = 0;
        String info=null;
        String response= HttpClientUtil.get(DOUBAN_SEARCH+"?q="+key);// 基于关键字搜索电影，获取id
        if (response!=null&&response.indexOf("{")!=-1){
            JSONObject jsonObject=JSONObject.fromObject(response);
            if (jsonObject!=null&&jsonObject.containsKey("subjects")){
                id= jsonObject.getJSONArray("subjects").getJSONObject(0).getInt("id");
            }

        }
        if (id==0){
            return id;
        }
        info=HttpClientUtil.get(DOUBAN_MOVIE+id); //获取电影的详细信息
        if (info!=null&&info.indexOf("{")!=-1){
            JSONObject jsonObject=JSONObject.fromObject(info);

            if (jsonObject!=null&&jsonObject.containsKey("rating")){
                DouBanInfo douBanInfo=new DouBanInfo();
                douBanInfo.setRating(jsonObject.getJSONObject("rating").getDouble("average"));
                douBanInfo.setUrl(jsonObject.getString("alt"));
                douBanInfo.setCountry(getName(jsonObject.getJSONArray("countries")));
                douBanInfo.setGenres(getName(jsonObject.getJSONArray("genres")));
                douBanInfo.setAka(getName(jsonObject.getJSONArray("aka")));
                douBanInfo.setImages(jsonObject.getString("images"));
                douBanInfo.setSubject(jsonObject.getString("title"));
                douBanInfo.setYear(jsonObject.getInt("year"));
                douBanInfo.setId(id);
                douBanInfo.setRatings_count(jsonObject.getInt("ratings_count"));
                douBanInfo.setSummary(jsonObject.getString("summary").replace("©豆瓣",""));

                douBanInfo.setCasts(getNameFromJson(jsonObject.getJSONArray("casts")));

                douBanInfo.setDirectors(getNameFromJson(jsonObject.getJSONArray("directors")));
                douBanInfoMapper.insertSelective(douBanInfo);
                }


        }
        return id;
    }
    private String getName(JSONArray jsonArray) {
        Iterator iterator=jsonArray.iterator();
        String name="";
        while (iterator.hasNext()){
            name=name+iterator.next()+"/";
        }
        return name.substring(0,name.lastIndexOf("/"));
    }
    private String getNameFromJson(JSONArray jsonArray){
        Iterator iterator=jsonArray.iterator();
        String name="";
        while (iterator.hasNext()){
            JSONObject jsonObject=JSONObject.fromObject(iterator.next());
            name=name+jsonObject.getString("name")+"/";
        }
         return name.substring(0,name.lastIndexOf("/"));
    }
}
