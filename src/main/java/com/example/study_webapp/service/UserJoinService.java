package com.example.study_webapp.service;

import java.util.Map;

public interface UserJoinService {

    Object insertUser(Map<String, Object> param) throws Exception;

    void updateUser(Map<String, Object> param) throws Exception;

    void deleteUser(Map<String, Object> param) throws Exception;

}
