package com.db2020.delivery.mapper;

import com.db2020.delivery.entity.DeliveryVO;

import java.util.HashMap;
import java.util.List;

public interface CautionMapper {

    public List<DeliveryVO> selectDetailCaution(HashMap<String, Object> param);

    public void insertCaution(HashMap<String, Object> param);

    public String selectCautionYn(String member_id);

    public void updateScoreY(HashMap<String, Object> param);

    public void updateScoreN(HashMap<String, Object> param);
}
