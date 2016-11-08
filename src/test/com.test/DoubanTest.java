package com.test;

import dao.FilmInfoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
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

    @Test
    public void addFilmInfo(){
        FilmInfo filmInfo=new FilmInfo();
        filmInfo.setTitle("test");
        filmInfo.setId(32434);
        filmInfo.setOrigin("wwteew");
        filmInfo.setContent("sdfsfds");
        filmInfoMapper.insertSelective(filmInfo);

    }
    @Test
    public void getFilm(){
        jsoupUtil.getGaoQing();
    }
    @Test
    public void getContet(){
        jsoupUtil.updateBillboard();
    }

    @Test
    public void check(){
        System.out.println(doubanService.checkDouban(26857715));
    }

}
