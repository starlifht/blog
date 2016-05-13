package handler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping(value = "/hello",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String index() {

        return "starcoollily";
    }
}
