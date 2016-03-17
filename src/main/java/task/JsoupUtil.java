package task;

import dao.DouBanInfoMapper;
import dao.FilmInfoMapper;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pojo.DouBanInfo;
import pojo.FilmInfo;
import tools.DoubanUtil;

import java.io.IOException;
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

    @Autowired
    private DouBanInfoMapper douBanInfoMapper;

    private final String AIDABAN_URL="http://www.aidaban.net/dianying";
    private final String GAOQING_URL="http://gaoqing.la/";
    private  final String USER_AGENT="Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:44.0) Gecko/20100101 Firefox/44.0";

    Logger logger= LogManager.getLogger(JsoupUtil.class.getName());
//
//    @Scheduled(cron = "0 0 * * * *")
//    public void getAidaban(){
//
//        Document document = null;
//        Document doc = null;
//        String url_child;
//        JSONObject moveiInfo;
//        try {
//            document = Jsoup.connect(AIDABAN_URL)
//                    .header("User-Agent",USER_AGENT)
//                    .timeout(5000).get();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        Elements elements = document.select("a.focus");
//        for (Element element : elements) {
//            url_child = element.attr("href");
//            logger.info(url_child);
//            try {
//                doc = Jsoup.connect(url_child)
//                        .header("User-Agent",USER_AGENT)
//                        .timeout(5000).get();
//                String title = doc.select(".article-title>a").text();
//                if (title.indexOf("r级")!=-1||title.indexOf("三级")!=-1){
//                    continue;
//                }
//                //  title = title.replaceAll("[\\[\\]/\\.]", " ");
//                String title_simple = null;
//                for (String x : title.replaceAll("[\\[\\]/\\.]", " ").split(" ")) {
//                    if (!"".equals(x)) {
//                        title_simple = x;
//                        break;
//                    }
//                }
//                doc.select(".wumii-hook").remove();
//                doc.select(".asb-post-footer").remove();
//                doc.select(".post-copyright").remove();
//                doc.select("p>.wp-image-5832").remove();
//                doc.select("strong").remove();
//                //  doc.select("blockquote").get(1).remove();
//                doc.select(".wp_keywordlink_affiliate a").attr("href","");
//                String content = doc.select(".article-content").html();
//                // content=content.replaceAll("(BT|bt)种子","");
//                moveiInfo = DoubanUtil.getMovieInfo(title_simple);
//                if (moveiInfo!=null&&moveiInfo.containsKey("rating")){
//                    Double rating = moveiInfo.getDouble("rating");
//
//                    FilmInfo filmInfo = new FilmInfo();
//                    filmInfo.setOrigin(url_child);
//                    filmInfo.setTitle(title);
//                    filmInfo.setDouban_rating(rating);
//                    filmInfo.setDouban(moveiInfo.toString());
//                    filmInfo.setLabel("ai");
//                    filmInfo.setContent(content);
//                    filmInfoMapper.insertSelective(filmInfo);
//                }
//
//                TimeUnit.MILLISECONDS.sleep(5000);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
    @Scheduled(cron = "0 * * * * *")
    public  void getGaoQing(){
        // 从 URL 直接加载 HTML 文档
        Document doc = null;
        String title=null;
      HashMap<String,Object> hashMap;
        String url=null;
        try {
            doc= Jsoup.connect(GAOQING_URL).header("User-Agent",USER_AGENT)
                    .timeout(5000).get();
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
                logger.info(url);
                Document document = Jsoup.connect(url).header("User-Agent",USER_AGENT)
                        .timeout(5000).get();
                title = document.select(".article_container > h1").text();
                String content = document.select("#post_content").html();
                hashMap = DoubanUtil.getMovieInfo(title.split(" ")[1]);
                if (!hashMap.isEmpty()){
                    DouBanInfo douBanInfo=new DouBanInfo();
                    douBanInfo.setId((Integer) hashMap.get("id"));
                    douBanInfo.setTitle((String) hashMap.get("title"));
                    douBanInfo.setCountry((String) hashMap.get("countries"));
                    douBanInfo.setGenres((String) hashMap.get("genres"));
                    douBanInfo.setRating((Double) hashMap.get("rating"));
                    douBanInfo.setAka((String) hashMap.get("aka"));
                    douBanInfo.setUrl((String) hashMap.get("url"));
                    douBanInfo.setYear((Integer) hashMap.get("year"));
                    douBanInfoMapper.insertSelective(douBanInfo);

                    FilmInfo filmInfo = new FilmInfo();
                    filmInfo.setOrigin(url);
                    filmInfo.setTitle(title);
                    filmInfo.setLabel("gq");
                    filmInfo.setContent(content);
                    filmInfoMapper.insertSelective(filmInfo);
                }


                TimeUnit.MILLISECONDS.sleep(5000);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {

    }}
