package com.db2020.delivery.mapper;

import com.db2020.delivery.entity.LogoutVO;
import com.db2020.delivery.entity.MainVO;
import sun.applet.Main;

import java.util.HashMap;
import java.util.List;

public interface DelivererMapper {


//    public void signDeliverer(HashMap<String, Object> param);
//
//    public DelivererVO signIn(HashMap<String, Object> param);
//
//    public List<HashMap<String, Object>> selectDelivererList(HashMap<String, Object> param);
//
//    public DelivererVO selectDeliverer_Info(String member_id);
//
//    public List<CautionVO> selectCaution_Info(String member_id);
//
//    public List<HashMap<String, Object>> selectDelivererGradeSc();
//
//    public void insertLocation(HashMap<String, Object> param);
//
//    public List<HashMap<String, Object>> selectDeliverer_newlist();
    public MainVO mainToday(HashMap<String, Object> param);

    public MainVO monthToday(HashMap<String, Object> param);

    public MainVO accumulate(HashMap<String, Object> param);

    public HashMap<String, Object> logout(HashMap<String, Object> param);
}
