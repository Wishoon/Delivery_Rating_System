package com.db2020.delivery.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class DeliveryVO {
    private String order_seq;
    private String delivery_startAt;
    private String delivery_completeAt;
    private String order_payment;
    private String order_total_price;
    private String order_summary;
    private String caution_title;
}
