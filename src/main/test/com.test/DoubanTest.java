package com.test;

import dao.DouBanInfoMapper;
import dao.FilmInfoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pojo.FilmInfo;
import service.DoubanService;
import service.FilmService;

/**
 * Created by star on 16-3-17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:applicationContext.xml",
"classpath:springContext.xml"})
public class DoubanTest {
    @Autowired
    private DouBanInfoMapper douBanInfoMapper;
    @Autowired
    private FilmInfoMapper filmInfoMapper;

    @Autowired
    private DoubanService doubanService;
    @Autowired
    private FilmService filmService;

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
        System.out.println(filmInfoMapper.getIDByDoubanID(25757187));
    }
    @Test
    public void getContet(){
        FilmInfo filmInfo=filmService.getContent(1);
        System.out.println("");
    }

}
