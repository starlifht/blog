package dao;

import org.apache.ibatis.annotations.Param;
import pojo.FilmInfo;

import java.util.List;

public interface FilmInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FilmInfo record);

    int insertSelective(FilmInfo record);

    FilmInfo selectByPrimaryKey(Integer id);
    List<FilmInfo> getAllByDate();
    List<FilmInfo> getAllByRating();
    List<FilmInfo> getAllDateByTitle(String title);



}