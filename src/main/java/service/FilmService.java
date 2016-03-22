package service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import dao.FilmInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.FilmInfo;

import java.util.HashMap;
import java.util.List;

/**
 * Created by star on 16-3-17.
 */
@Service
public class FilmService {
    @Autowired
    private FilmInfoMapper filmInfoMapper;

    public HashMap getFlimList(Integer pageNo,Integer pageSize){

            HashMap hashMap=new HashMap();
            pageNo = pageNo == null?1:pageNo;
            pageSize = pageSize == null?10:pageSize;
            PageHelper.startPage(pageNo, pageSize);
            List<FilmInfo> list = filmInfoMapper.getAllByDate();
            //用PageInfo对结果进行包装
            PageInfo<FilmInfo> page = new PageInfo<FilmInfo>(list);
            hashMap.put("totalpage",page.getPages());
            hashMap.put("pageNo",pageNo);
            hashMap.put("list",list);
            hashMap.put("pageSize",pageSize);
            return hashMap;
    }
    public FilmInfo getContent(Integer id){


        return filmInfoMapper.selectByPrimaryKey(id);

    }
}
