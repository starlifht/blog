package handler;

import dao.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by star on 15-11-12.
 */
@Controller
@RequestMapping("/")
public class Hello {
//    @RequestMapping(value = "hello",method = RequestMethod.GET)
//    public String getHH(){
//        return "hello";
//    }
    @Autowired
    private UserInfoMapper userInfoMapper;

    @RequestMapping("/hello")
    public String index() {
        System.out.println(userInfoMapper.getAll("B15835F133FF2E27C7CB28117BFAE8F4"));
        return "hello";
    }
}
