package dao;

import pojo.DouBanInfo;

public interface DouBanInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DouBanInfo record);

    int insertSelective(DouBanInfo record);

    DouBanInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DouBanInfo record);

    int updateByPrimaryKey(DouBanInfo record);
}