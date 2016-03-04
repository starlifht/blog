package dao;

import org.apache.ibatis.annotations.Param;
import pojo.FilmInfo;

import java.util.List;

public interface FilmInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FilmInfo record);

    int insertSelective(FilmInfo record);

    FilmInfo selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(FilmInfo record);

    int updateByPrimaryKey(FilmInfo record);
    int getAllCount();
    int getCountByTitle(String title);
    int getCountByLabel(String label);

    List<FilmInfo> getAllDateByTitle(@Param("title") String title,@Param("offset") int offset, @Param("limit") int limit);
    List<FilmInfo> getAllDate(@Param("offset") int offset, @Param("limit") int limit);
    List<FilmInfo> getAllDateByLabel(@Param("label")String label,@Param("offset") int offset, @Param("limit") int limit);
    List<FilmInfo> getALLRatingByLabel(@Param("label")String label,@Param("offset") int offset, @Param("limit") int limit);


}