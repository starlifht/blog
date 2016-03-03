package handler;

import dao.FilmInfoMapper;

import net.sf.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pojo.FilmInfo;
import tools.DoubanUtil;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by star on 15-11-19.
 */
@Controller
@RequestMapping("/film")
public class FilmHandler {
    @Autowired
    private FilmInfoMapper filmInfoMapper;
    @RequestMapping(value = "/getAllCount",method = {RequestMethod.GET,RequestMethod.POST},produces = "application/json;charset=utf-8")
    @ResponseBody
    public JSONObject getAllCount() {
        JSONObject jsonpObject=new JSONObject();
        jsonpObject.put("status",0);
        jsonpObject.put("count",filmInfoMapper.getAllCount());

        return jsonpObject;
    }
    @RequestMapping(value = "/getCount/{label}",method = {RequestMethod.GET,RequestMethod.POST},produces = "application/json;charset=utf-8")
    @ResponseBody
    public JSONObject getCountByLabel(@PathVariable("label")String label) {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("status",0);
        jsonObject.put("count",filmInfoMapper.getCountByLabel(label));

        return jsonObject;
    }
    @RequestMapping(value = "/date",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String getDAte(@RequestParam(value = "time")Date date) {



        return date.toString();
    }
    @RequestMapping(value = "/getAllByLimit/{offset}/{limit}",method = {RequestMethod.GET,RequestMethod.POST},produces = "application/json;charset=utf-8")
    @ResponseBody
    public JSONObject getAllByLimit(
                                    @PathVariable("offset")int offset,
                                    @PathVariable("limit")int limit) {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("status",0);
//        Map hashMap=new HashMap();
//        hashMap.put("ind",1);
//        hashMap.put("len",5);
        jsonObject.put("data",filmInfoMapper.getAllByLimit(offset,limit));

        return jsonObject;
    }
    @RequestMapping(value = "/getAllByLimit/{label}/{offset}/{limit}",method = {RequestMethod.GET,RequestMethod.POST},produces = "application/json;charset=utf-8")
    @ResponseBody
    public JSONObject getAllByLimitLabel(@PathVariable("label")String label,
                                         @PathVariable("offset")int offset,
                                    @PathVariable("limit")int limit) {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("status",0);
        jsonObject.put("data",filmInfoMapper.getAllByLimitByLabel(label,offset,limit));
        return jsonObject;
    }
    @RequestMapping(value = "/getAllByLimitRating/{label}/{offset}/{limit}",method = {RequestMethod.GET,RequestMethod.POST},produces = "application/json;charset=utf-8")
    @ResponseBody
    public JSONObject getAllByLimitLabelRating(@PathVariable("label")String label,
                                         @PathVariable("offset")int offset,
                                         @PathVariable("limit")int limit) {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("status",0);
        jsonObject.put("data",filmInfoMapper.getAllByLimitByLabelRating(label,offset,limit));
        return jsonObject;
    }
    @RequestMapping(value = "/getByPriKey/{id}",method = {RequestMethod.GET,RequestMethod.POST},produces = "application/json;charset=utf-8")
    @ResponseBody
    public JSONObject getByPriKey(@PathVariable("id")int id) {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("status",0);

        jsonObject.put("data",filmInfoMapper.selectByPrimaryKey(id));

        return jsonObject;
    }



    @RequestMapping(value = "/insert",method = {RequestMethod.GET})
    @ResponseBody
    public String insertFilm(){
        String url="http://gaoqing.la/";

        // 从 URL 直接加载 HTML 文档
        Document doc = null;
        String title=null;
        JSONObject moveiInfo;

        try {
            doc= Jsoup.connect(url).timeout(5000).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements links = doc.select("#post_container a[href~=http://gaoqing.la/(.+?).html]");
            HashSet<String> hashSet=new HashSet<String>();
            for (Element element:links){
                hashSet.add(element.attr("href"));

            }

            Iterator<String> iterator=hashSet.iterator();
            while (iterator.hasNext()){
                try {
                    url = iterator.next();
                    Document document = Jsoup.connect(url).get();
                    title = document.select(".article_container > h1").text();
                    String content = document.select("#post_content").html();
                    moveiInfo = DoubanUtil.getMovieInfo(title.split(" ")[1]);
                    if (moveiInfo!=null&&moveiInfo.containsKey("rating")){
                        Double rating = moveiInfo.getDouble("rating");
                        FilmInfo filmInfo = new FilmInfo();
                        filmInfo.setOrigin(url);
                        filmInfo.setTitle(title);
                        filmInfo.setDouban_rating(rating);
                        filmInfo.setDouban(moveiInfo.toString());
                        filmInfo.setLabel("gaoqing");
                        filmInfo.setContent(content);
                        filmInfoMapper.insertSelective(filmInfo);
                    }


                    TimeUnit.MILLISECONDS.sleep(5000);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }

        return "ok";
    }
}
