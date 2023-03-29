package board.login.controller;

import board.login.dto.MembersDto;
import board.login.dto.RegistDto;
import board.login.dto.ResultDto;
import board.login.service.LoginService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RestController
@RequiredArgsConstructor
public class LoginController {
    @Autowired
    private LoginService loginService;

    @RequestMapping("/login/login.do")
    public ModelAndView login() throws Exception{
        ModelAndView mv = new ModelAndView("login/login");

        return mv;
    }

    @RequestMapping("/login/register.do")
    public ModelAndView register() throws Exception{
        ModelAndView mv = new ModelAndView("login/register");

        return mv;
    }

    @RequestMapping(value="/login/register.ajax", method= RequestMethod.POST, produces = "application/json; charset=utf8")
    @ResponseBody
    public String saveRegisterInfo(@RequestBody RegistDto mbr) throws Exception{
        ObjectMapper mapper = new ObjectMapper();

        ResultDto result = new ResultDto();

        log.info(mbr.toString());

        String rtn = loginService.saveRegisterInfo(mbr);
        if (rtn.equals("Ok")) {
            result.setStatus("Ok");
            result.setErrMsg("");
        }
        else {
            result.setStatus("Error");
            result.setErrMsg(rtn);
        }

        String jsonString = mapper.writeValueAsString(result);

        log.info("result = " + result);

        return jsonString;
    }

    @RequestMapping(value="/login/certification.do", method=RequestMethod.GET)
    @ResponseBody
    public ModelAndView certification(MembersDto mbr) throws Exception{
        ModelAndView mv = null;

        log.info(mbr.toString());

        String rtn = loginService.saveCertification(mbr);
        if (rtn.equals("Ok")) {
            mv = new ModelAndView("login/welcome");
        }
        else {
            mv = new ModelAndView("login/denied");
            mv.addObject("errMsg", rtn);
        }

        log.info("result = " + rtn);

        return mv;
    }

    @RequestMapping(value="/login/checkUser.ajax", method=RequestMethod.POST, produces = "application/json; charset=utf8")
    @ResponseBody
    public String checkUser(@RequestBody MembersDto mbr) throws Exception{
        ObjectMapper mapper = new ObjectMapper();

        ResultDto result = new ResultDto();

        log.info(mbr.toString());

        String rtn = loginService.checkUser(mbr);
        if (rtn.equals("Ok")) {
            result.setStatus("Ok");
            result.setErrMsg("");
        }
        else {
            result.setStatus("Error");
            result.setErrMsg(rtn);
        }

        String jsonString = mapper.writeValueAsString(result);

        log.info("result = " + result);

        return jsonString;
    }

}
