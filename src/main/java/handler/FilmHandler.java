package handler;

import dao.FilmInfoMapper;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by star on 15-11-19.
 */
@Controller
@RequestMapping("/film")
public class FilmHandler {
    @Autowired
    private FilmInfoMapper filmInfoMapper;
    @RequestMapping(value = "/getAllCount",method = {RequestMethod.GET,RequestMethod.POST},produces = "application/json;charset=utf-8")
    @ResponseBody
    public JSONObject getAllCount() {
        JSONObject jsonpObject=new JSONObject();
        jsonpObject.put("status",0);
        jsonpObject.put("count",filmInfoMapper.getAllCount());

        return jsonpObject;
    }
    @RequestMapping(value = "/getCount/{label}",method = {RequestMethod.GET,RequestMethod.POST},produces = "application/json;charset=utf-8")
    @ResponseBody
    public JSONObject getCountByLabel(@PathVariable("label")String label) {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("status",0);
        jsonObject.put("count",filmInfoMapper.getCountByLabel(label));

        return jsonObject;
    }
    @RequestMapping(value = "/date",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String getDAte(@RequestParam(value = "time")Date date) {



        return date.toString();
    }
    @RequestMapping(value = "/getAllByLimit/{offset}/{limit}",method = {RequestMethod.GET,RequestMethod.POST},produces = "application/json;charset=utf-8")
    @ResponseBody
    public JSONObject getAllByLimit(
                                    @PathVariable("offset")int offset,
                                    @PathVariable("limit")int limit) {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("status",0);
//        Map hashMap=new HashMap();
//        hashMap.put("ind",1);
//        hashMap.put("len",5);
        jsonObject.put("data",filmInfoMapper.getAllByLimit(offset,limit));

        return jsonObject;
    }
    @RequestMapping(value = "/getAllByLimit/{label}/{offset}/{limit}",method = {RequestMethod.GET,RequestMethod.POST},produces = "application/json;charset=utf-8")
    @ResponseBody
    public JSONObject getAllByLimitLabel(@PathVariable("label")String label,
                                         @PathVariable("offset")int offset,
                                    @PathVariable("limit")int limit) {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("status",0);
//        Map hashMap=new HashMap();
//        hashMap.put("ind",1);
//        hashMap.put("len",5);
        jsonObject.put("data",filmInfoMapper.getAllByLimitByLabel(label,offset,limit));

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
