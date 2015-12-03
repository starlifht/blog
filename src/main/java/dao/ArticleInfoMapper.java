package dao;

import org.apache.ibatis.annotations.Param;
import pojo.ArticleInfo;
import pojo.FilmInfo;

import java.util.List;

public interface ArticleInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleInfo record);

    int insertSelective(ArticleInfo record);

    ArticleInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleInfo record);

    int updateByPrimaryKeyWithBLOBs(ArticleInfo record);

    int updateByPrimaryKey(ArticleInfo record);
    int getCount(@Param("label") String label);
    List<FilmInfo> getAll(@Param("offset") int offset, @Param("limit") int limit);

    List<FilmInfo> getByLabel(@Param("label")String label, @Param("offset") int offset, @Param("limit") int limit);

}