package com.example.study_webapp.controller.user;

import com.example.study_webapp.config.jwt.JwtConfig;
import com.example.study_webapp.service.board.BoardService;
import com.example.study_webapp.service.board.BoardServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Controller
public class UserController {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BoardService boardService;

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    StringRedisTemplate stringRedisTemplate;


    @GetMapping(value = {"/", "/tag/{word}"})
    public ModelAndView mainViewPage(HttpServletRequest request,
                                     HttpServletResponse response,
                                     @PathVariable(required = false) String word) throws Exception {

        ModelAndView mv = new ModelAndView("/main/main.tiles");
        HashMap<String,Object> paramMap = new HashMap<String,Object>();

        GithubApi githubApi = new GithubApi();
        List<Map<String,Object>> githubApiIssueComments = githubApi.getIssueRecentComments(jwtConfig.getOauthToken(), jwtConfig.getRepoName());

        //해시태그
        HashSet<String> set = hashtagService.selectAllHashTag();
        if(!set.isEmpty()) {
            mv.addObject("hash", set);
        }
        paramMap.put("hashTag", word);
        //댓글
        mv.addObject("comment", githubApiIssueComments);
        //목록
        mv.addObject("list", boardService.selectBoard(paramMap));

        return mv;
    }


}
