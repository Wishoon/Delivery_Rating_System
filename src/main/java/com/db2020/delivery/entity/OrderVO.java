package com.db2020.delivery.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Data
public class OrderVO {

    private int order_seq;
    private String order_createAt;
    private String order_payment;
    private String order_total_price;
    private String order_summary;
    private String grade_nm;
    private String delivery_state;
}