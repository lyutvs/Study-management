package com.example.study_webapp.controller.board;

import com.example.study_webapp.service.board.BoardService;
import com.example.study_webapp.service.file.FileUploadService;
import com.example.study_webapp.service.hashtag.HashtagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;
    @Autowired
    private FileUploadService fileuploadService;
    @Autowired
    private HashtagService hashtagService;




    @RequestMapping(value="/board/{idx}" , method = {RequestMethod.GET})
    @ResponseBody
    public ModelAndView selectDetail(HttpServletRequest request,
                                     HttpServletResponse response,
                                     @RequestParam Map<String,Object> param,
                                     @PathVariable int idx) throws Exception {

        ModelAndView mv = new ModelAndView("board/boardVw.tiles");

        param.put("IDX", idx);
        mv.addObject("param", param);
        mv.addObject("list", boardService.selectBoard(param));
        mv.addObject("data", boardService.selectDetail(param));

        List<Map<String, Object>> list = fileuploadService.selectAttachFileListByIDX(param);

        if(!list.isEmpty() || list != null ) {
            mv.addObject("files", list);
        }

        //해시태그 obj
        Map<String,Object> hash = hashtagService.selectOneHashTag(param);
        if(hash != null) {
            if(hash.size() > 0) {
                String[] arr = ((String) hash.get("CONTENTS")).split(",");
                mv.addObject("hash", arr);
            }
        }

        boardService.updateCount(param);
        return mv;
    }

    /*
     * 어디에 적을 것인지
     * */
    @RequestMapping(value="/board/edit", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER')")
    public ModelAndView writeForm(Authentication authentication, @RequestParam Map<String,Object>param) throws Exception {
        ModelAndView mv = new ModelAndView("board/boardEd.tiles");

        mv.addObject("list", boardService.selectBoard(param));
        mv.addObject("category", boardService.selectCategory());
        mv.addObject("username", authentication.getPrincipal().toString());

        mv.addObject("mode", "new");
        return mv;
    }



}
