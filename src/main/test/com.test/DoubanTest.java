package com.test;

import dao.DouBanInfoMapper;
import dao.FilmInfoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pojo.DouBanInfo;
import pojo.FilmInfo;

/**
 * Created by star on 16-3-17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class DoubanTest {
    @Autowired
    private DouBanInfoMapper douBanInfoMapper;
    @Autowired
    private FilmInfoMapper filmInfoMapper;
    @Test
    public void  addDoubanInfo(){
        DouBanInfo douBanInfo=new DouBanInfo();
        douBanInfo.setId(777);
        douBanInfo.setTitle("dsfsdfsdf");
        douBanInfoMapper.insertSelective(douBanInfo);
    }
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
        System.out.println(filmInfoMapper.getAllByDate());
    }
}
