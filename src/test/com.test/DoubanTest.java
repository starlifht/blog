package com.test;

import dao.DouBanInfoMapper;
import dao.FilmInfoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pojo.DouBanInfo;
import pojo.FilmInfo;
import service.DoubanService;
import task.JsoupUtil;

/**
 * Created by star on 16-3-17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:applicationContext.xml",
"classpath:springContext.xml"})
public class DoubanTest {

    @Autowired
    private FilmInfoMapper filmInfoMapper;

    @Autowired
    private DoubanService doubanService;

    @Autowired
    private JsoupUtil jsoupUtil;
    @Autowired
    private DouBanInfoMapper douBanInfoMapper;
    @Test
    public void addFilmInfo(){
        FilmInfo filmInfo=new FilmInfo();
        filmInfo.setTitle("test");
        filmInfo.setId(32434);
        filmInfo.setOrigin("http://www.languangdy.com/other/WEB-DL/1707.html");
        filmInfo.setContent("sdfsfds");
        System.out.println(filmInfoMapper.insertSelective(filmInfo));

    }
    @Test
    public void  setDoubanService(){
        DouBanInfo douBanInfo=new DouBanInfo();
        douBanInfo.setId(6872936);
        douBanInfo.setRatingcount(9999);
        System.out.println(douBanInfoMapper.insertSelective(douBanInfo));
    }
    @Test
    public void getGaoQing(){
        jsoupUtil.getGaoQing();
    }
    @Test
    public void getLanGuang(){
        jsoupUtil.getLanGuang();
    }
    @Test
    public void getBillboard(){
        jsoupUtil.updateBillboard();
    }
    @Test
    public void check(){
        System.out.println(doubanService.checkDouban(26857715));
    }

}
