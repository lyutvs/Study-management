package com.example.study_webapp.service.hashtag;

import java.util.Map;

public interface HashTagService {

    void insertHashTag(Map<String, Object> param) throws Exception;

    Map<String, Object> selectOneHashTag(Map<String, Object> param) throws Exception;

    void updateHashTag(Map<String, Object> param) throws Exception;



}
