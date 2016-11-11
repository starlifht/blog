package dao;

import pojo.FilmInfo;
import java.util.List;

public interface FilmInfoMapper {
    int deleteByPrimaryKey(Integer id);
    int insert(FilmInfo record);
    int insertSelective(FilmInfo record);
    FilmInfo selectByPrimaryKey(Integer id);
    FilmInfo getContentByDoubanID(Integer douban_id);
    Integer getIDByDoubanID(Integer douban_id);
    List<FilmInfo> getAllByDate();
    List<FilmInfo> getAllByRating();
    List<FilmInfo> getAllByYear();
    List<FilmInfo> getAllDateByTitle(String title);

}