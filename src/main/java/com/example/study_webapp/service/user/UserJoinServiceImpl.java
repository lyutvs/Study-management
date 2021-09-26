package com.example.study_webapp.service.user;

import java.util.Map;

import com.example.study_webapp.dao.CommonDao;
import com.example.study_webapp.service.user.UserJoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("UserJoinService")
public class UserJoinServiceImpl implements UserJoinService {

    @Autowired
    private CommonDao commonDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Object insertUser(Map<String, Object> param) throws Exception {

        String password = (String) param.get("password");
        param.put("password", passwordEncoder.encode(password));

        commonDao.insert("user.insertUser", param);
        return null;
    }

    @Override
    public void updateUser(Map<String, Object> param) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteUser(Map<String, Object> param) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public Object selectUserNameCehck(Map<String, Object> param) throws Exception {
        param.put("USE", commonDao.selectCount("user.selectUserNameCehck", param));
        return param;
    }
}
