package com.example.study_webapp.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("commonDao")
public class CommonDao {

    @Autowired
    private SqlSessionTemplate sqlSession;

    //protected void printQueryId(String queryId){
    //	if(log.isDebugEnabled()){ log.debug("\t QueryId \t: " + queryId); } }

    public Object insert(String queryId, Object params){
        //printQueryId(queryId);
        return sqlSession.insert(queryId, params);
    }

}
