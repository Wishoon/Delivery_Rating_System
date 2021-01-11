package com.db2020.delivery.mapper;

import com.db2020.delivery.entity.DelivererVO;
import com.db2020.delivery.entity.MainVO;

import java.util.HashMap;

public interface AuthMapper {

    public HashMap<String, Object> deliverer_signIn(HashMap<String, Object> param);

    public HashMap<String, Object> shop_signIn(HashMap<String, Object> param);

//    public HashMap<String, Object> mainPage(HashMap<String, Object> param);


}
