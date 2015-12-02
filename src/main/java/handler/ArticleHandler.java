package handler;

import dao.ArticleInfoMapper;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
}
