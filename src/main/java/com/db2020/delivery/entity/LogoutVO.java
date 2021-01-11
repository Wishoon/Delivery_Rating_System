package com.db2020.delivery.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class LogoutVO {
    private int deliverer_todayCnt;
    private int caution_todayCnt;
    private int deliverer_score;
    private String grade_nm;
    private int deliverer_todayPrice;
}
