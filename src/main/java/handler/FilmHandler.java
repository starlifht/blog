package handler;

import dao.FilmInfoMapper;

import net.sf.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pojo.FilmInfo;
import service.FilmService;
import tools.DoubanUtil;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by star on 15-11-19.
 */
@Controller
@RequestMapping("/film")
public class FilmHandler {
    @Autowired
    private FilmInfoMapper filmInfoMapper;
    @Autowired
    private FilmService filmService;
    @RequestMapping(value = "/{pagesize}/{pageno}",method = RequestMethod.GET)
    public String getAll(ModelMap modelMap,
                         @PathVariable(value = "pagesize")Integer pagesize,
                         @PathVariable(value = "pageno")Integer pageno){
        HashMap hashMap=filmService.getFlimList(pageno,pagesize);
        modelMap.addAttribute("totalpage",hashMap.get("totalpage"));
        modelMap.addAttribute("pageNo",hashMap.get("pageNo"));
        modelMap.addAttribute("pageSize",hashMap.get("pageSize"));
        modelMap.addAttribute("list",hashMap.get("list"));
        return "film_list";
    }

    @RequestMapping(value = "/getCount/{label}",method = {RequestMethod.GET,RequestMethod.POST},produces = "application/json;charset=utf-8")
    @ResponseBody
    public JSONObject getCountByLabel(@PathVariable("label")String label,
                                      @RequestParam(value = "title",required = false)String title) {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("status",0);
        if (!"".equals(title)){
            try {
                jsonObject.put("data",filmInfoMapper.getCountByTitle(URLDecoder.decode(title,"utf8")));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return jsonObject;
        }
        if ("all".equals(label)){
            jsonObject.put("count",filmInfoMapper.getAllCount());
        }else{
            jsonObject.put("count",filmInfoMapper.getCountByLabel(label));
        }


        return jsonObject;
    }

    //时间排序
    @RequestMapping(value = "/date/{label}/{offset}/{limit}",method = {RequestMethod.GET,RequestMethod.POST},produces = "application/json;charset=utf-8")
    @ResponseBody
    public JSONObject getAllByLimitLabel(@PathVariable("label")String label,
                                         @PathVariable("offset")int offset,
                                         @PathVariable("limit")int limit,
                                         @RequestParam(value = "title",required = false)String title) {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("status",0);
        if (!"".equals(title)){
            try {
                jsonObject.put("data",filmInfoMapper.getAllDateByTitle(URLDecoder.decode(title,"utf8"),offset,limit));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return jsonObject;
        }
        if ("all".equals(label)){
            jsonObject.put("data",filmInfoMapper.getAllDate(offset,limit));

        }else{
            jsonObject.put("data",filmInfoMapper.getAllDateByLabel(label,offset,limit));
        }
        return jsonObject;
    }

    //按评分排序
    @RequestMapping(value = "/rating/{label}/{offset}/{limit}",method = {RequestMethod.GET,RequestMethod.POST},produces = "application/json;charset=utf-8")
    @ResponseBody
    public JSONObject getAllByLimitLabelRating(@PathVariable("label")String label,
                                         @PathVariable("offset")int offset,
                                         @PathVariable("limit")int limit) {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("status",0);
        jsonObject.put("data",filmInfoMapper.getALLRatingByLabel(label,offset,limit));
        return jsonObject;
    }
    @RequestMapping(value = "/getByPriKey/{id}",method = {RequestMethod.GET,RequestMethod.POST},produces = "application/json;charset=utf-8")
    @ResponseBody
    public JSONObject getByPriKey(@PathVariable("id")int id) {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("status",0);

        jsonObject.put("data",filmInfoMapper.selectByPrimaryKey(id));

        return jsonObject;
    }




}
