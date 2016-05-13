package dao;

import pojo.Billboard;

import java.util.List;

public interface BillboardMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Billboard record);

    int insertSelective(Billboard record);

    Billboard selectByPrimaryKey(Integer id);
    List<Billboard> selectByWeeks(Integer weeks);

    int updateByPrimaryKeySelective(Billboard record);

    int updateByPrimaryKey(Billboard record);
}