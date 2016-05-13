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
import pojo.FilmInfo;
import service.DoubanService;

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
    private DoubanService doubanService;

    @Autowired
    private BillboardMapper billboardMapper;

    private final String AIDABAN_URL="http://www.aidaban.net/dianying";
    private final String GAOQING_URL="http://gaoqing.la/";
    private  final String USER_AGENT="Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:44.0) Gecko/20100101 Firefox/44.0";

    Logger logger= LogManager.getLogger(JsoupUtil.class.getName());
    @Scheduled(cron = "0 46 9 * * *")
    public void updateBillboard(){
        try {
            Document  doc= Jsoup.connect("http://movie.douban.com")
                    .header("User-Agent",USER_AGENT)
                    .timeout(5000).get();
            Elements element=doc.select(".billboard-bd tr td a");
            Pattern pattern=Pattern.compile("[0-9]+");
            Matcher matcher;
            int i=1;
            int weeks=Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
            for (Element element1:element){
                matcher=pattern.matcher(element1.attr("href"));
                if (matcher.find()){
                    Billboard billboard=new Billboard();
                    billboard.setDoubanId(Integer.valueOf(matcher.group()));
                    billboard.setTitle(element1.text());
                    billboard.setOrders(i);
                    billboard.setWeeks(weeks);
                    billboardMapper.insertSelective(billboard);
                    i++;

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   // @Scheduled(cron = "0 0 */2 * * *")
    @Scheduled(cron = "0 22 15 * * *")
    public  void getGaoQing(){
        // 从 URL 直接加载 HTML 文档
        Document doc = null;
        String title=null;

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
                Elements elements = document.select("#post_content a[href~=magnet(.+?)]");
                String content="";
                if (elements.isEmpty()){
                    continue;
                }
                for (Element element:elements){
                    content=content+"<p>"+element.outerHtml()+"</p>";
                }
                int id =doubanService.getDoubanID(title.split(" ")[1]);
                if (id!=0&&doubanService.setDoubanInfo(id)){

                    FilmInfo filmInfo = new FilmInfo();
                    filmInfo.setOrigin(url);
                    filmInfo.setTitle(title);
                    filmInfo.setLabel("gq");
                    filmInfo.setContent(content);
                    filmInfo.setDouban_id(id);
                    if(filmInfoMapper.insertSelective(filmInfo)!=1) {
                        logger.error("film入库失败"+title+url);
                    }
                }else {
                    logger.error("[豆瓣无信息]"+title+url);
                }
                TimeUnit.MILLISECONDS.sleep(5000);
            }catch(Exception e){
                e.printStackTrace();
            }

        }
    }


    public static void main(String[] args) {

        Date date=new Date();
        Calendar calendar=Calendar.getInstance();
        System.out.println(calendar.get(Calendar.WEEK_OF_YEAR));

    }}
