package service;

import dao.DouBanInfoMapper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pojo.DouBanInfo;
import tools.HttpClientUtil;
import tools.Params;

import java.io.IOException;
import java.util.Iterator;
import java.util.regex.Matcher;

/**
 * Created by star on 16-3-24.
 */
@Service
public class DoubanService {
    private Logger logger= LogManager.getLogger(DoubanService.class.getName());
    @Autowired
    private DouBanInfoMapper douBanInfoMapper;

    private   final  String DOUBAN_SEARCH="https://api.douban.com/v2/movie/search";
    private   final  String DOUBAN_SEARCH_WEB="https://movie.douban.com/subject_search?search_text=";
    private  final  String DOUBAN_MOVIE="https://api.douban.com/v2/movie/subject/";
    private  final String USER_AGENT="Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:44.0) Gecko/20100101 Firefox/44.0";
    private  Matcher matcher;

    @Value("#{configProperties['douban.subject']}")
    private   String DOUBAN_MOVIE_WEB;
    public static void  main(String[] args){
        new DoubanService().getDoubanInfo(234);
    }
    public int getDoubanURL(String key){
    int id = 0;

    String response= HttpClientUtil.get(DOUBAN_SEARCH+"?q="+key);// 基于关键字搜索电影，获取id
        System.out.println(response);
    if (response!=null&&response.indexOf("{")!=-1){
        JSONObject jsonObject=JSONObject.fromObject(response);
        if (jsonObject!=null&&jsonObject.containsKey("subjects")){
            id= jsonObject.getJSONArray("subjects").getJSONObject(0).getInt("id");
        }

    }

        return id;
}
    public Integer getDoubanID_web(String key){
        Document document = null;
        try {
           document= Jsoup.connect(DOUBAN_SEARCH_WEB+key).header("User-Agent",USER_AGENT)
                    .timeout(5000).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
          Elements elements =document.select("#content .nbg");
            for (Element e:elements){
                System.out.println(e.attr("href"));
                matcher=Params.DoubanIdPattern.matcher(e.attr("href"));

                while(matcher.find()){
                    return Integer.parseInt(matcher.group(1));
                }

            }
            return null;
    }
    public DouBanInfo  getDoubanInfo(int doubanID){
        Document document = null;
        DouBanInfo douBanInfo = new DouBanInfo();

        try {
            document= Jsoup.connect(DOUBAN_MOVIE_WEB+doubanID).header("User-Agent",USER_AGENT)
                    .timeout(10000).get();
            douBanInfo.setId(doubanID);
            douBanInfo.setUrl(DOUBAN_MOVIE_WEB+doubanID);
            douBanInfo.setSubject(document.select("#content span[property=v:itemreviewed]").first().text());
            douBanInfo.setYear(Integer.valueOf(document.select("#content h1 .year").first().text().replace("(","").replace(")","")));
            String rating=document.select("strong[property=v:average").text();
            if (!rating.equals("")){
                douBanInfo.setRating(Float.valueOf(rating));//rating
                douBanInfo.setRatingcount(Integer.valueOf(document.select("span[property=v:votes]").text()));//votes

            }
            douBanInfo.setImages(document.select("img[rel=v:image]").first().attr("src"));//picture
            douBanInfo.setSummary(document.select("span[property=v:summary]").first().text().trim());//summary
            Element element=document.getElementById("info");
            String info=element.html(element.html().replaceAll("<br>","|")).text().replaceAll("\\s*", "");
            for (String a:info.split("\\|")){

                String key = a.split(":")[0];
                String value = a.split(":")[1];

                if (key.equals("导演")) {
                    douBanInfo.setDirectors(value);
                }  
                if (key.equals("主演")) {
                    douBanInfo.setCasts(value);
                }
                if (key.equals("类型")) {
                    douBanInfo.setGenres(value);
                }
                if (key.equals("制片国家/地区")) {
                    douBanInfo.setCountry(value);
                }
                if (key.equals("又名")) {
                    douBanInfo.setAka(value);
                }
                if (key.equals("上映日期")||key.equals("首播")) {
                    douBanInfo.setReleasedate(value);
                }
                if (key.equals("片长")||key.equals("单集片长")) {
                    douBanInfo.setRuntime(value);
                }
                if (key.equals("IMDb链接")) {
                    douBanInfo.setImdb(value);
                }
            }


        } catch (IOException e) {
            logger.error(doubanID+"|"+e.getMessage());
            e.printStackTrace();
        }
        return douBanInfo;
    }
    public boolean  setDoubanInfo(int id) throws Exception{
        boolean flag=false;
        String info=null;
        info=HttpClientUtil.get(DOUBAN_MOVIE+id); //获取电影的详细信息
        if (info!=null&&info.indexOf("{")!=-1){
            JSONObject jsonObject=JSONObject.fromObject(info);

            if (jsonObject!=null&&jsonObject.containsKey("rating")){
                DouBanInfo douBanInfo=new DouBanInfo();
                douBanInfo.setRating((float) jsonObject.getJSONObject("rating").getDouble("average"));
                douBanInfo.setUrl(jsonObject.getString("alt"));
                douBanInfo.setCountry(getName(jsonObject.getJSONArray("countries")));
                douBanInfo.setGenres(getName(jsonObject.getJSONArray("genres")));
                douBanInfo.setAka(getName(jsonObject.getJSONArray("aka")));
                douBanInfo.setImages(jsonObject.getString("images"));
                douBanInfo.setSubject(jsonObject.getString("title"));
                douBanInfo.setYear(jsonObject.getInt("year"));
                douBanInfo.setId(id);
                douBanInfo.setRatingcount(jsonObject.getInt("ratings_count"));
                douBanInfo.setSummary(jsonObject.getString("summary").replace("©豆瓣",""));
                douBanInfo.setCasts(getNameFromJson(jsonObject.getJSONArray("casts")));
                douBanInfo.setDirectors(getNameFromJson(jsonObject.getJSONArray("directors")));

                  if (douBanInfoMapper.insertSelective(douBanInfo)>0){
                        flag=true;
                  }
                }


        }


        return flag;
    }
    public boolean checkDouban(int doubanID){
        if (douBanInfoMapper.selectByPrimaryKey(doubanID)!=null){
            return true;
        }else {
            return false;
        }
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
