package task;

import dao.FilmInfoMapper;
import net.sf.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pojo.FilmInfo;
import tools.DoubanUtil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/**
 * Created by star on 16-3-1.
 */
@Component
public class JsoupUtil {
    @Autowired
    private FilmInfoMapper filmInfoMapper;

     private String m;
    public  JsoupUtil(String s){
        this.m=s;
    }

    public  void getGaoQing(){
        String url="http://gaoqing.la/";

        // 从 URL 直接加载 HTML 文档
        Document doc = null;
        String title=null;
        JSONObject movieInfo;
        try {
            doc= Jsoup.connect(url).get();

            Elements links = doc.select("a[href~=http://gaoqing.la/(.+?).html]");
            HashSet<String> hashSet=new HashSet<String>();
            for (Element element:links){
                hashSet.add(element.attr("href"));

            }

            Iterator<String> iterator=hashSet.iterator();
            while (iterator.hasNext()){
                url=iterator.next();
                Document document=Jsoup.connect(url).get();
                title=document.select(".article_container > h1").text();
                String content=document.select("#post_content").html();
                movieInfo= DoubanUtil.getMovieInfo(title.split(" ")[1]);


                Double rating= (Double) movieInfo.get("rating");
                FilmInfo filmInfo=new FilmInfo();
                filmInfo.setOrigin(url);
                filmInfo.setDouban_rating(rating);
                filmInfo.setDouban(movieInfo);
                filmInfo.setLabel("gaoqing");
                filmInfo.setContent(content);
                filmInfoMapper.insertSelective(filmInfo);

                TimeUnit.MILLISECONDS.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


//    public static void main(String[] args){
//
//          JsoupUtil jsoupUtil=new JsoupUtil("ww.abaidu.com");
//    }
}
