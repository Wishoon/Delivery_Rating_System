package com.db2020.delivery.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class CautionDetailVO {
    private String order_seq;
    private String delivery_createAt;
    private String delivery_completeAt;
    private String member_nm;
    private String caution_title;
    private String caution_reason;
}
