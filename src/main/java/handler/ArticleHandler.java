package handler;

import dao.ArticleInfoMapper;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pojo.ArticleInfo;

/**
 * Created by star on 15-12-1.
 */
@Controller
@RequestMapping("/article")
public class ArticleHandler {
    @Autowired
    private ArticleInfoMapper articleInfoMapper;
    @RequestMapping(value = "/add",method = {RequestMethod.GET,RequestMethod.POST},produces = "application/json;charset=utf-8")
    @ResponseBody
    private JSONObject addArticle(@RequestBody JSONObject request){
        JSONObject jsonObject=new JSONObject();
        ArticleInfo articleInfo=new ArticleInfo();
        articleInfo.setTitle(request.getString("title"));
        articleInfo.setContent(request.getString("content"));
       int status= articleInfoMapper.insertSelective(articleInfo);
        jsonObject.put("status",status);
        return jsonObject;
    }
    @RequestMapping(value = "/get/{offset}/{limit}",method = {RequestMethod.GET,RequestMethod.POST},produces = "application/json;charset=utf-8")
    @ResponseBody
    private JSONObject getArticle(
                                  @PathVariable("offset")int offset,
                                  @PathVariable("limit")int limit){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("data",articleInfoMapper.getAll(offset,limit));
        return jsonObject;
    }
    @RequestMapping(value = "/get/{label}/{offset}/{limit}",method = {RequestMethod.GET,RequestMethod.POST},produces = "application/json;charset=utf-8")
    @ResponseBody
    private JSONObject getByLabel(@PathVariable("label")String label,
                                  @PathVariable("offset")int offset,
                                  @PathVariable("limit")int limit){
      JSONObject jsonObject=new JSONObject();
        jsonObject.put("data",articleInfoMapper.getByLabel(label,offset,limit));
        return jsonObject;
    }
    @RequestMapping(value = "/getByPriKey/{id}",method = {RequestMethod.GET,RequestMethod.POST},produces = "application/json;charset=utf-8")
    @ResponseBody
    private JSONObject getByPriKeyById(@PathVariable("id")int id){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("data",articleInfoMapper.selectByPrimaryKey(id));
        return jsonObject;
    }
    @RequestMapping(value = "/getCount/{label}",method = {RequestMethod.GET,RequestMethod.POST},produces = "application/json;charset=utf-8")
    @ResponseBody
    private JSONObject getCountByLabel(@PathVariable("label")String label){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("count",articleInfoMapper.getCount(label));
        return jsonObject;
    }
    @RequestMapping(value = "/getCount",method = {RequestMethod.GET,RequestMethod.POST},produces = "application/json;charset=utf-8")
    @ResponseBody
    private JSONObject getCount(){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("count",articleInfoMapper.getCount(null));
        return jsonObject;
    }
}
