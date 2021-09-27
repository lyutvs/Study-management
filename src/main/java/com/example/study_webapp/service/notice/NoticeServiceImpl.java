package com.example.study_webapp.service.notice;

import com.example.study_webapp.dao.CommonDao;
import com.example.study_webapp.model.util.PagingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("NoticeService")
public class NoticeServiceImpl implements NoticeService{

    @Autowired
    private CommonDao commonDao;

    @SuppressWarnings("unchecked")
    @Override
    public List<Map<String, Object>> selectBoard(Map<String, Object> param) throws Exception {
        PagingUtil paging = new PagingUtil();
        int index = 1;
        if(param.containsKey("pageIndex")) {
            index = Integer.parseInt(param.get("pageIndex").toString());
            paging.setPageIndex(index);

        }
        paging.countPaging(index, 5);
        param.put("page",paging);

        return commonDao.selectList("notice.selectBoard", param);
    }

    @Override
    public Map<String, Object> selectDetail(Map<String, Object> param) throws Exception {
        return null;
    }

    @Override
    public Object insertBoard(Map<String, Object> param) throws Exception {
        return null;
    }

    @Override
    public void updateCount(Map<String, Object> param) throws Exception {

    }

    @Override
    public void updateBoard(Map<String, Object> param) throws Exception {

    }

    @Override
    public void deleteBoard(Map<String, Object> param) throws Exception {

    }

    @Override
    public Map<String, Object> selectMaxIdx() throws Exception {
        return null;
    }

    @Override
    public List<Map<String, Object>> serchBoard(Map<String, Object> param) throws Exception {
        return null;
    }

    @Override
    public Map<String, Object> selectCount(Map<String, Object> param) throws Exception {
        return null;
    }
}
