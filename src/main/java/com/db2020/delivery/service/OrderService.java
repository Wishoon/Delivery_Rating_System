package com.db2020.delivery.service;

import com.db2020.delivery.entity.DeliveryVO;
import com.db2020.delivery.entity.OrderVO;
import com.db2020.delivery.mapper.OrderMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class OrderService {

    SqlSession sqlSession;
    OrderMapper orderMapper;

    public OrderService(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
        orderMapper = sqlSession.getMapper(OrderMapper.class);
    }

    public List<HashMap<String, Object>> selectOrderList(HashMap<String, Object> param){
        List<HashMap<String, Object>> params = orderMapper.selectOrderList(param);
        return params;
    }

    public HashMap<String, Object> orderInfo(String order_seq){
        return orderMapper.orderInfo(order_seq);
    }

    public List<DeliveryVO> selectCautionList(HashMap<String, Object> param){

        List<DeliveryVO> data = orderMapper.selectOrderEndList(param);
        System.out.println(data);
        return data;
    }

    public void insertMatch(HashMap<String, Object> param){
        String today;
        Date date = new Date();
        SimpleDateFormat createAt = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, 0);
        today = createAt.format(cal.getTime());
        param.put("delivery_startAt", today);

        cal.add(Calendar.MINUTE, 50);
        today = createAt.format(cal.getTime());
        param.put("delivery_expectedAt", today);

        orderMapper.insertMatch(param);
    }

    public void startMatch(HashMap<String, Object> param){
        String today;
        Date date = new Date();
        SimpleDateFormat createAt = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, 0);
        today = createAt.format(cal.getTime());
        param.put("delivery_startAt", today);

        System.out.println(param.get("delivery_startAt"));
        orderMapper.startMatch(param);
    }

    public void endMatch(HashMap<String, Object> param){
        String today;
        System.out.println("endMatch");
        Date date = new Date();
        SimpleDateFormat createAt = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, 0);
        today = createAt.format(cal.getTime());
        param.put("delivery_completeAt", today);
        orderMapper.endMatch(param);
    }

    public String orderMatchYn(String delivery_seq){

        String orderMatchYn = orderMapper.orderMatchYn(delivery_seq);

        return orderMatchYn;
    }
}
