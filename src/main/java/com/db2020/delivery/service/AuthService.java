package com.db2020.delivery.service;

import com.db2020.delivery.entity.LogoutVO;
import com.db2020.delivery.entity.MainVO;
import com.db2020.delivery.mapper.AuthMapper;
import com.db2020.delivery.mapper.DelivererMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import javax.print.attribute.HashPrintJobAttributeSet;
import java.awt.event.AdjustmentEvent;
import java.util.HashMap;

@Service
public class AuthService {

    SqlSession sqlSession;
    AuthMapper authMapper;
    DelivererMapper delivererMapper;

    public AuthService(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
        authMapper = sqlSession.getMapper(AuthMapper.class);
        delivererMapper = sqlSession.getMapper(DelivererMapper.class);
    }
    public HashMap<String, Object> deliverer_signIn(HashMap<String, Object> param){
        return authMapper.deliverer_signIn(param);
    }

    public HashMap<String, Object> shop_signIn(HashMap<String, Object> param){
        return authMapper.shop_signIn(param);
    }

    public MainVO mainPage(HashMap<String, Object> param) {

        MainVO data = delivererMapper.mainToday(param);
        MainVO data1 = delivererMapper.monthToday(param);
        MainVO data2 = delivererMapper.accumulate(param);
        data.setDeliverer_monthCnt(data1.getDeliverer_monthCnt());
        data.setDeliverer_totalCnt(data2.getDeliverer_totalCnt());
        data.setCaution_monthCnt(data1.getCaution_monthCnt());
        data.setCaution_totalCnt(data2.getCaution_totalCnt());
        data.setDeliverer_monthPrice(data1.getDeliverer_monthPrice());
        data.setDeliverer_totalPrice(data2.getDeliverer_totalPrice());

        return data;
    }

    public HashMap<String, Object> deliverer_logout(HashMap<String, Object> param) {

        return delivererMapper.logout(param);
    }
}
