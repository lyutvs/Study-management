package com.example.study_webapp.service;

import com.example.study_webapp.dao.CommonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("UserJoinService")
public class UserJoinServiceImpl implements UserJoinService {

    @Autowired
    private CommonDao commonDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Object insertUser(Map<String, Object> param) throws Exception {

        String password = (String) param.get("password");
        param.put("password", passwordEncoder.encode(password));

        commonDao.insert("user.insertUser", param);
        return null;
    }

    @Override
    public void updateUser(Map<String, Object> param) throws Exception {

    }

    @Override
    public void deleteUser(Map<String, Object> param) throws Exception {

    }

    @Override
    public Object selectUserNameCehck(Map<String, Object> param) throws Exception {
        return null;
    }
}
