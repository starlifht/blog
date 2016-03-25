package handler;

import dao.FilmInfoMapper;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pojo.FilmInfo;
import service.DoubanService;
import service.FilmService;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

/**
 * Created by star on 15-11-19.
 */
@Controller
@RequestMapping("/film")
public class FilmHandler {

    @Autowired
    private FilmService filmService;
    @Autowired
    private DoubanService doubanService;

    @RequestMapping(value = "/{label}/{pagesize}/{pageno}",method = RequestMethod.GET)
    public String getAll(ModelMap modelMap,
                         @PathVariable(value = "label")String label,
                         @PathVariable(value = "pagesize")Integer pagesize,
                         @PathVariable(value = "pageno")Integer pageno,
                         @RequestParam(value = "title",required = false)String title){
        HashMap hashMap=filmService.getFilmList(pageno,pagesize,label,title);
        modelMap.addAttribute("totalpage",hashMap.get("totalpage"));
        modelMap.addAttribute("pageNo",hashMap.get("pageNo"));
        modelMap.addAttribute("pageSize",hashMap.get("pageSize"));
        modelMap.addAttribute("list",hashMap.get("list"));
        modelMap.addAttribute("label",label);
        return "film_list";
    }
    @RequestMapping(value = "/content/{id}",method = RequestMethod.GET)
    public String getContent(ModelMap modelMap,
                         @PathVariable(value = "id")Integer id){
        FilmInfo filmInfo=filmService.getContent(id);
        if (filmInfo==null){
            modelMap.addAttribute("error","获取电影信息失败");
            return "error";
        }
        modelMap.addAttribute("filminfo",filmInfo);

        return "film_content";
    }





}
