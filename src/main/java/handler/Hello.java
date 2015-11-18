package handler;

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

    @RequestMapping("/hello")
    public String index() {
        return "hello";
    }
}
