package com.example.study_webapp.controller.notice;

import com.example.study_webapp.model.util.file.FileUploadService;
import com.example.study_webapp.service.hashtag.HashtagService;
import com.example.study_webapp.service.notice.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private HashtagService hashtagService;


    /**
     * 리스트 선택 하는 로직 인데 쪼끔 이상하니까 나중에 리팩토링 해야 할거 같음
     * 응아아아 entry 망했닿ㅎ...
     */

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @RequestMapping(value = {"/notice", "/notice/{word}"}, produces = "text/plain;charset=UTF-8", method = {RequestMethod.POST, RequestMethod.GET})
    @Repository
    public ModelAndView selectBoard(@RequestParam Map<String, Object> param, @PathVariable(required = false) String word) throws Exception {

        if (word != null) {
            param.put("hashSerch", "true");
            param.put("hashWord", word);
        }
        ModelAndView mv = new ModelAndView("notice/noticeLs.tiles");

        if (param.get("MENU_CODE") == null) {
            param.put("MENU_CODE", "B");
        }

        mv.addObject("hash", hashtagService.selectHashTag(param));
        mv.addObject("list", noticeService.selectBoard(param));

        Map<String, Object> map = noticeService.selectCount(param);
        mv.addObject("param", param);
        mv.addObject("page", param.get("page"));
        mv.addObject("totalCount", map.get("TOTAL_COUNT"));

        return mv;

    }

    /**
     * 확인 하는거인데 이것도 뭔가 이상하니까 이상해..
     */

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @RequestMapping(value="/notice/detail/{idx}" , method = {RequestMethod.POST,RequestMethod.GET})
    @Repository
    public ModelAndView selectDetail(HttpServletRequest request, @RequestParam Map<String,Object>param, @PathVariable int idx) throws Exception {

        ModelAndView mv = new ModelAndView("board/boardVw.titles");

        param.put("IDX", idx);
        mv.addObject("data", noticeService.selectDetail(param));

        // obj 파일
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        list = fileUploadService.selectAttachFileListByIDX(param);
        if(!list.isEmpty() || list != null ) {
            mv.addObject("files", list);
        }

        // obj 해시태그
        Map<String,Object> tag = hashtagService.selectOneHashTag(param);
        String[] tagArray = ((String) tag.get("CONTENTS")).split(",");
        mv.addObject("hash", tagArray);

        noticeService.updateCount(param);
        mv.addObject("param", param);


        return mv;

    }




}