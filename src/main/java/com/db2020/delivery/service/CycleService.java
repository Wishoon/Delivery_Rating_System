package com.db2020.delivery.service;

import com.db2020.delivery.entity.CycleDetailVO;
import com.db2020.delivery.entity.CycleVO;
import com.db2020.delivery.mapper.CycleMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class CycleService {

    SqlSession sqlSession;
    CycleMapper cycleMapper;

    public CycleService(SqlSession sqlSession){

        this.sqlSession = sqlSession;
        cycleMapper = sqlSession.getMapper(CycleMapper.class);
    }

    public List<CycleVO> selectCycle_list(HashMap<String, Object> param){

        return cycleMapper.selectCycle_list(param);
    }

    public CycleDetailVO selectCycle_Info(HashMap<String, Object> param){

        return cycleMapper.selectCycle_Info(param);
    }
}
