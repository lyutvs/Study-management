package com.example.study_webapp.service.notice;

import java.util.List;
import java.util.Map;

public interface NoticeService {

    List<Map<String,Object>> selectBoard(Map<String, Object> param) throws Exception;

    Map<String,Object> selectDetail(Map<String, Object> param) throws Exception;

    Object insertBoard(Map<String, Object> param) throws Exception;

    void updateCount(Map<String, Object> param) throws Exception;

}
