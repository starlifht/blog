package handler;

import dao.BillboardMapper;
import dao.FilmInfoMapper;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.Billboard;

import java.util.*;

/**
 * Created by star on 16-4-8.
 */
@Controller
@RequestMapping("/billboard")
public class BillBoardHandler {
    @Autowired
    private BillboardMapper billboardMapper;
    @Autowired
    private FilmInfoMapper filmInfoMapper;

    @RequestMapping(value = "/get",method = {RequestMethod.GET})
    @ResponseBody
    public JSONObject get(){
        JSONObject jsonObject=new JSONObject();
        List<Billboard> billboardList=billboardMapper.selectByWeeks(Calendar.getInstance().get(Calendar.WEEK_OF_YEAR));
        Iterator iterator=billboardList.iterator();
        ArrayList arrayList=new ArrayList();

        while (iterator.hasNext()){
            Billboard json= (Billboard) iterator.next();
            HashMap hashMap=new HashMap();
            hashMap.put("title",json.getTitle());
            Integer id;
            if ( (id=filmInfoMapper.getIDByDoubanID(json.getDoubanId()))!=null){
                hashMap.put("id",id);

            }else{
                hashMap.put("id",0);
            }
            arrayList.add(hashMap);


        }
        jsonObject.put("status",0);
        jsonObject.put("data",arrayList);
        return jsonObject;
    }
}
