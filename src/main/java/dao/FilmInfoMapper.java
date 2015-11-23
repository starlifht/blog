package dao;

import org.apache.ibatis.annotations.Param;
import pojo.FilmInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface FilmInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FilmInfo record);

    int insertSelective(FilmInfo record);

    FilmInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FilmInfo record);

    int updateByPrimaryKey(FilmInfo record);
    int getAllCount();
    int getCountByLabel(String label);
    List<FilmInfo> getAllByLimit(@Param("offset") int offset, @Param("limit") int limit);
    List<FilmInfo> getAllByLimitByLabel(@Param("label")String label,@Param("offset") int offset, @Param("limit") int limit);


}