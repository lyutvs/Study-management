package com.example.study_webapp.controller.board;

import com.example.study_webapp.service.board.BoardService;
import com.example.study_webapp.service.file.FileUploadService;
import com.example.study_webapp.service.hashtag.HashtagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;
    @Autowired
    private FileUploadService fileuploadService;
    @Autowired
    private HashtagService hashtagService;

}
