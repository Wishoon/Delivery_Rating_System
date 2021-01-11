package com.db2020.delivery.mapper;

import com.db2020.delivery.entity.DeliveryVO;
import com.db2020.delivery.entity.OrderVO;

import java.util.HashMap;
import java.util.List;

public interface OrderMapper {

    public List<HashMap<String, Object>> selectOrderList(HashMap<String, Object> param);

    public HashMap<String, Object> orderInfo(String order_seq);

    public List<DeliveryVO> selectOrderEndList(HashMap<String, Object> param);

    public void insertMatch(HashMap<String, Object> param);

    public void startMatch(HashMap<String, Object> param);

    public void endMatch(HashMap<String, Object> param);

    public String orderMatchYn(String delivery_seq);
}
