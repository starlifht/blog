package handler;

import dao.BillboardMapper;
import dao.DouBanInfoMapper;
import dao.FilmInfoMapper;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.Billboard;
import pojo.DouBanInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by star on 16-4-8.
 */
@Controller
@RequestMapping("/billboard")
public class BillBoardHandler {
    private Logger logger= LogManager.getLogger(BillBoardHandler.class.getName());
    @Autowired
    private BillboardMapper billboardMapper;
    @Autowired
    private FilmInfoMapper filmInfoMapper;
    @Autowired
    private DouBanInfoMapper douBanInfoMapper;

    @RequestMapping(value = "/get",method = {RequestMethod.GET})
    @ResponseBody
    public Map<String ,Object> get(HttpServletRequest request){
        Map<String ,Object> jsonObject=new HashedMap();
        jsonObject.put("status",0);
        if (request.getSession().getAttribute("billboard")!=null){

            jsonObject.put("cache","yes");
            jsonObject.put("data",request.getSession().getAttribute("billboard"));
            return jsonObject;
        }
        List<Billboard> billboardList=billboardMapper.selectByWeeks(Calendar.getInstance().get(Calendar.WEEK_OF_YEAR));
        Iterator iterator=billboardList.iterator();
        ArrayList arrayList=new ArrayList();

        while (iterator.hasNext()){
            Billboard billboard= (Billboard) iterator.next();
            HashMap hashMap=new HashMap();
            hashMap.put("title",billboard.getTitle());
            DouBanInfo douBanInfo= douBanInfoMapper.selectByPrimaryKey(billboard.getDoubanId());
            if (douBanInfo!=null){
                hashMap.put("rating",douBanInfo.getRating());
                hashMap.put("doubanid",billboard.getDoubanId());
            }
            Integer id;

            if ( (id=filmInfoMapper.getIDByDoubanID(billboard.getDoubanId()))!=null){
                hashMap.put("id",id);

            }else{
                hashMap.put("id",0);

            }
            arrayList.add(hashMap);


        }
        jsonObject.put("data",arrayList);
        jsonObject.put("cache","no");
        request.getSession().setAttribute("billboard",arrayList);
        return jsonObject;
    }
}
