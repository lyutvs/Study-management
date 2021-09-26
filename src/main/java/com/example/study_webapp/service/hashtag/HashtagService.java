package com.example.study_webapp.service.hashtag;

import java.util.HashSet;
import java.util.Map;

public interface HashtagService {

    void insertHashTag(Map<String, Object> param) throws Exception;

    Map<String, Object> selectOneHashTag(Map<String, Object> param) throws Exception;

    void updateHashTag(Map<String, Object> param) throws Exception;

    HashSet<String> selectHashTag(Map<String, Object> param) throws Exception;

    HashSet<String> selectAllHashTag() throws Exception;

    void deleteHashTag(Map<String, Object> param)  throws Exception;

}
