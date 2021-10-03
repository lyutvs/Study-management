package com.example.study_webapp.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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

    public Object update(String queryId, Object params){
        //printQueryId(queryId);
        return sqlSession.update(queryId, params);
    }

    public Object delete(String queryId, Object params){
        //printQueryId(queryId);
        return sqlSession.delete(queryId, params);
    }

    public Map selectOne(String queryId){
        //printQueryId(queryId);
        return sqlSession.selectOne(queryId);
    }

    public Integer selectCount(String queryId, Object params){
        //printQueryId(queryId);
        return sqlSession.selectOne(queryId, params);
    }

    public Map<String,Object> selectOne(String queryId, Object params){
        //printQueryId(queryId);
        return sqlSession.selectOne(queryId, params);
    }

    public Object selectOptional(String queryId, Object params){
        //printQueryId(queryId);
        return sqlSession.selectOne(queryId, params);
    }

    @SuppressWarnings("rawtypes")
    public List selectList(String queryId){
        //printQueryId(queryId);
        return sqlSession.selectList(queryId);
    }

    @SuppressWarnings("rawtypes")
    public List selectList(String queryId, Object params){
        //printQueryId(queryId);
        return sqlSession.selectList(queryId,params);
    }

}