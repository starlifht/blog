package task;

import dao.BillboardMapper;
import dao.DouBanInfoMapper;
import dao.FilmInfoMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pojo.Billboard;
import pojo.DouBanInfo;
import pojo.FilmInfo;
import service.DoubanService;
import tools.Params;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by star on 16-3-1.
 */
@Component
public class JsoupUtil {
    @Autowired
    private FilmInfoMapper filmInfoMapper;
    @Autowired
    private DouBanInfoMapper douBanInfoMapper;
    @Autowired
    private DoubanService doubanService;
    @Autowired
    private BillboardMapper billboardMapper;

    private final String GAOQING_URL="http://gaoqing.la/";
    private  final String USER_AGENT="Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:44.0) Gecko/20100101 Firefox/44.0";
    private final int TIME_OUT=10000;
    Logger logger= LogManager.getLogger(JsoupUtil.class.getName());
    @Scheduled(cron = "0 46 9 * * *")
    public void updateBillboard(){
        try {
            Document  doc= Jsoup.connect("http://movie.douban.com")
                    .header("User-Agent",USER_AGENT)
                    .timeout(TIME_OUT).get();
            Elements element=doc.select(".billboard-bd tr td a");
            Pattern pattern=Pattern.compile("[0-9]+");
            Matcher matcher;
            int i=1;
            int weeks=Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
            for (Element element1:element){
                matcher=pattern.matcher(element1.attr("href"));
                if (matcher.find()){
                    int douban_ID=Integer.valueOf(matcher.group());
                    Billboard billboard=new Billboard();
                    billboard.setDoubanId(douban_ID);
                    billboard.setTitle(element1.text());
                    billboard.setOrders(i);
                    billboard.setWeeks(weeks);
                    billboardMapper.insertSelective(billboard);
                    DouBanInfo douBanInfo=doubanService.getDoubanInfo(douban_ID);
                        if (douBanInfo!=null){
                            if (douBanInfoMapper.insertSelective(douBanInfo)!=1){
                                logger.error("Fail to insert  Douban Info "+douban_ID);
                            }
                        }

                    logger.info(element1.text()+"|"+douban_ID);
                    i++;
                    TimeUnit.MILLISECONDS.sleep(5000);

                }

            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }
//    @Scheduled(cron = "0 0 */2 * * *")
    @Scheduled(cron = "0 50 17 * * *")
    public  void getGaoQing(){
        // 从 URL 直接加载 HTML 文档
        Document doc = null;
        String title=null;

        String url=null;
        try {
            doc= Jsoup.connect(GAOQING_URL).header("User-Agent",USER_AGENT)
                    .timeout(TIME_OUT).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements links = doc.select(".mainleft #post_container a[href~=http://gaoqing.la/(.+?).html]");
        LinkedHashSet<String> hashSet=new LinkedHashSet<String>();
        for (int i=links.size();i>0;i--){

            hashSet.add(links.get(i-1).attr("href"));

        }
        Iterator<String> iterator=hashSet.iterator();
        while (iterator.hasNext()){
            int doubanID = 0;

            try {
                url = iterator.next();
                logger.info(url);
                Document document = Jsoup.connect(url).header("User-Agent",USER_AGENT)
                        .timeout(TIME_OUT).get();
                title = document.select(".article_container > h1").text();
                Elements elements = document.select("#post_content a[href~=magnet(.+?)]");//下载地址
                Matcher matcher= Params.DoubanIdPattern.matcher(document.getElementById("post_content").text());

                String content="";
                if (elements.isEmpty()){
                    continue;
                }
                for (Element element:elements){
                    content=content+"<p>"+element.outerHtml()+"</p>";
                }
                if (matcher.find()){
                    doubanID= Integer.parseInt(matcher.group(1));
                }

                boolean isDouban=false;
                if (!doubanService.checkDouban(doubanID)){
                    DouBanInfo douBanInfo=doubanService.getDoubanInfo(doubanID);
                    if (douBanInfo!=null){
                        if (douBanInfoMapper.insertSelective(douBanInfo)!=1){
                            logger.error("Fail to insert  Douban Info "+doubanID);
                        }else {
                            isDouban=true;
                            logger.info("Success to insert  Douban Info "+doubanID);
                        }
                    }else {
                        logger.error("Can not find  Douban Info "+doubanID);

                    }
                }else {
                    isDouban=true;
                }
                if (isDouban){
                    FilmInfo filmInfo = new FilmInfo();
                    filmInfo.setOrigin(url);
                    filmInfo.setTitle(title);
                    filmInfo.setLabel("gq");
                    filmInfo.setContent(content);
                    filmInfo.setDouban_id(doubanID);
                    if(filmInfoMapper.insertSelective(filmInfo)<=0) {
                        logger.error("Film insert fail "+title.split(" ")[1]+url);
                    }else{
                        logger.info("Film insert success "+title.split(" ")[1]+url);
                    }
                }


                TimeUnit.MILLISECONDS.sleep(5000);
            }catch(Exception e){
                logger.error("GaoQing|"+url +"|"+e.getMessage());
                e.printStackTrace();
            }

        }
    }
//    @Scheduled(cron = "0 0 */2 * * *")
    @Scheduled(cron = "0 34 17 * * *")
    public  void getLanGuang(){
        // 从 URL 直接加载 HTML 文档
        Document doc = null;
        String title=null;

        String url=null;

        try {
            doc= Jsoup.connect("http://www.languangdy.com").header("User-Agent",USER_AGENT)
                    .timeout(TIME_OUT).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements links = doc.select(".mainleft #post_container a[href~=http://www.languangdy.com/(.+?).html]");
        LinkedHashSet<String> hashSet=new LinkedHashSet<String>();
        for (int i=links.size();i>0;i--){

            hashSet.add(links.get(i-1).attr("href"));

        }
        Iterator<String> iterator=hashSet.iterator();
        while (iterator.hasNext()){
            int doubanID = 0;
            try {
                url = iterator.next();
                logger.info(url);
                Document document = Jsoup.connect(url).header("User-Agent",USER_AGENT)
                        .timeout(TIME_OUT).get();
                title = document.select(".article_container > h1").text();
                logger.info(title);
                //下载地址获取
                Elements elements = document.select(".context .dldiv a[href~=magnet(.+?)]");
                String content="";
                if (elements.isEmpty()){
                    continue;
                }else{
                    for (Element element:elements){
                        content=content+"<p>"+element.outerHtml()+"</p>";
                    }
                }
                 //豆瓣 ID匹配
                Matcher matcher= Params.DoubanIdPattern.matcher(document.getElementById("post_content").text());


                if (matcher.find()){
                    doubanID= Integer.parseInt(matcher.group(1));
                    logger.info(doubanID);
                }

                boolean isDouban=false;
                if (!doubanService.checkDouban(doubanID)){
                    DouBanInfo douBanInfo=doubanService.getDoubanInfo(doubanID);
                    if (douBanInfo!=null){
                        if (douBanInfoMapper.insertSelective(douBanInfo)!=1){
                            logger.error("Fail to insert  Douban "+doubanID);
                        }else {
                            isDouban=true;
                            logger.info("Success to insert Douban "+doubanID);
                        }
                    }else {
                        logger.error("Can not find  Douban  "+doubanID);

                    }
                }else {
                    isDouban=true;
                    logger.info("Douban have existed "+doubanID);
                }
                if (isDouban){
                    FilmInfo filmInfo = new FilmInfo();
                    filmInfo.setOrigin(url);
                    filmInfo.setTitle(title);
                    filmInfo.setLabel("languang");
                    filmInfo.setContent(content);
                    filmInfo.setDouban_id(doubanID);
                    if(filmInfoMapper.insertSelective(filmInfo)<=0) {
                        logger.error("Film insert fail "+title+"|"+url);
                    }else{
                        logger.info("Film insert success "+title+"|"+url);
                    }
                }


                TimeUnit.MILLISECONDS.sleep(5000);
            }catch(Exception e){
                logger.error("LanGuang|"+doubanID+"|"+url +"|"+e.getMessage());
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {

        Date date=new Date();
        Calendar calendar=Calendar.getInstance();
        System.out.println(calendar.get(Calendar.WEEK_OF_YEAR));

    }}
