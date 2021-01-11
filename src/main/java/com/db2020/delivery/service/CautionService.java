package com.db2020.delivery.service;

import com.db2020.delivery.entity.DeliveryVO;
import com.db2020.delivery.mapper.CautionMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class CautionService {

    SqlSession sqlSession;
    CautionMapper cautionMapper;

    public CautionService(SqlSession sqlSession){
        this.sqlSession = sqlSession;
        cautionMapper = sqlSession.getMapper(CautionMapper.class);
    }

    public List<DeliveryVO> selectDetailCaution(HashMap<String, Object> param){
        System.out.println(param.toString());
        return cautionMapper.selectDetailCaution(param);
    }

    public void insertCaution(HashMap<String, Object> param){
        System.out.println(param.toString());

        cautionMapper.insertCaution(param);
        String cautionYn = cautionMapper.selectCautionYn((String)param.get("delivery_seq"));
        System.out.println("hi" + cautionYn);
        if(cautionYn.equals("Y")){
            System.out.println("afnwenaflawefaw");
            cautionMapper.updateScoreY(param);
        } else{
            cautionMapper.updateScoreN(param);
        }
    }
}
