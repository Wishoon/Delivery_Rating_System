package com.db2020.delivery.mapper;

import com.db2020.delivery.entity.CycleDetailVO;
import com.db2020.delivery.entity.CycleVO;

import java.util.HashMap;
import java.util.List;

public interface CycleMapper {

    public List<CycleVO> selectCycle_list(HashMap<String, Object> param);

    public CycleDetailVO selectCycle_Info(HashMap<String, Object> param);
}
