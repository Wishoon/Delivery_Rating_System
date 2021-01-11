package com.db2020.delivery.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class CycleDetailVO {

    private String member_id;
    private String member_nm;
    private String motorcycle_seq;
    private String employ_motorcycle_seq;
    private String motorcycle_type;
    private String motorcycle_createAt;
    private String motorcycle_rentStart;
    private String motorcycle_rentEnd;
    private String motorcycle_rent_price;
    private String motorcycle_owner;
    private String motorcycle_registNum;
}
