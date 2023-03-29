package board.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;

@Controller
public class MainController {
    @RequestMapping("/main/main.do")
    public ModelAndView black() throws Exception{
        ModelAndView mv = new ModelAndView("main/main");
        mv.addObject("timestamp", new Timestamp(System.currentTimeMillis()));
        return mv;
    }
}
