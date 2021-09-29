package com.example.study_webapp.controller.notice;

import com.example.study_webapp.service.file.FileUploadService;
import com.example.study_webapp.service.hashtag.HashtagService;
import com.example.study_webapp.service.notice.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
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
   // @Repository
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
    @ResponseBody
    public ModelAndView selectDetail(HttpServletRequest request, @RequestParam Map<String,Object>param, @PathVariable int idx) throws Exception {
        ModelAndView mv = new ModelAndView("board/boardVw.tiles");

        param.put("IDX", idx);
        mv.addObject("data", noticeService.selectDetail(param));

        //파일 obj
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        list = fileUploadService.selectAttachFileListByIDX(param);
        if(!list.isEmpty() || list != null ) {
            mv.addObject("files", list);
        }

        //해시태그 obj
        Map<String,Object> tag = hashtagService.selectOneHashTag(param);
        String[] tagArray = ((String) tag.get("CONTENTS")).split(",");
        mv.addObject("hash", tagArray);

        noticeService.updateCount(param);
        mv.addObject("param", param);
        return mv;


    }

    /*
     * 어따 쓸건지 장하는거
     * */
    @RequestMapping(value="/notice/write")
    public ModelAndView  writeForm() throws Exception {
        ModelAndView mv = new ModelAndView("notice/noticeEd.tiles");
        mv.addObject("mode", "new");
        return mv;
    }

    /**
     * 보드 추가 하는거
     */

    @RequestMapping(value = "/notice/insert", method = RequestMethod.POST)
    private String insertBoard(@RequestParam Map<String,Object> param) throws Exception {

        noticeService.insertBoard(param);

        Map<String, Object> map = noticeService.selectMaxIdx();

        param.put("IDX", map.get("IDX"));
        if (param.get("HASHTAG") != null) {
            hashtagService.insertHashTag(param);
        }

        param.put("BOARD_IDX", map.get("IDX"));
        return "detail/" + map.get("IDX");

    }

    /**
     * 수정 하는거
     */

    @RequestMapping(value="/notice/modify/{idx}", method = RequestMethod.GET)
    public ModelAndView modifyForm(HttpServletRequest request, @PathVariable int idx) throws Exception {
        ModelAndView mv = new ModelAndView("notice/noticeEd.tiles");

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("IDX", idx);

        List<Map<String, Object>> files = null;
        files = fileUploadService.selectAttachFileListByIDX(param);
        // "false".equals(files.isEmpty()) || (Integer)files.get(0).get("FILE_GROUP") > 0
        if (!files.isEmpty()) {

            int FILE_GROUP = (Integer) files.get(0).get("FILE_GROUP");
            if (FILE_GROUP > 0) {
                mv.addObject("FILE_GROUP", FILE_GROUP);
                mv.addObject("files", files);
            }
        }

        //해시태그 obj
        Map<String,Object> tag = hashtagService.selectOneHashTag(param);
        String[] tagArray = ((String) tag.get("CONTENTS")).split(",");
        mv.addObject("hash", tagArray);
        mv.addObject("HASHTAG", (String)tag.get("CONTENTS"));

        mv.addObject("data", noticeService.selectDetail(param));
        mv.addObject("mode", "modify");
        return mv;
    }


    /**
     * 업데이트 하는 거
     *
     */
    @RequestMapping(value="/notice/update", method = RequestMethod.POST)
    @ResponseBody
    public String updateBoard(HttpServletRequest request, @RequestParam Map<String,Object>param) throws Exception {
        noticeService.updateBoard(param);
        hashtagService.updateHashTag(param);
        String idx = (String) param.get("IDX");
        return idx;
    }

}