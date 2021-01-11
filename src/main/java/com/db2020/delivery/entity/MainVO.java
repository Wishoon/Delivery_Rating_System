package com.db2020.delivery.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class MainVO {

    // 배달 횟수
    private String deliverer_todayCnt;
    private String deliverer_monthCnt;
    private String deliverer_totalCnt;
    // 경고 횟수
    private String caution_todayCnt;
    private String caution_monthCnt;
    private String caution_totalCnt;
    // 배달원 점수 및 경고
    private String deliverer_score;
    private String deliverer_grade;
    // 배달원 가격
    private int deliverer_todayPrice;
    private int deliverer_monthPrice;
    private int deliverer_totalPrice;
}
