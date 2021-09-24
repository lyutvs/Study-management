package com.example.study_webapp.controller.user;

import com.example.study_webapp.service.user.UserJoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class UserJoinController {

    @Autowired
    private UserJoinService joinService;


    @RequestMapping(value = "/userjoin", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView loginJoinPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //ModelAndView mv = new ModelAndView("login/join.tiles");
        ModelAndView mv = new ModelAndView("userjoin");

        return mv;
    }

    @RequestMapping(value = "/join", method = {RequestMethod.POST,RequestMethod.GET}, produces = "application/json; charset=UTF-8")
    public ModelAndView loginJoin(@RequestBody  Map<String,Object> param) throws Exception {

        ModelAndView mv = new ModelAndView("userlogin");
        joinService.insertUser(param);

        return mv;
    }


}
