package com.db2020.delivery.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class CycleVO {

    private String motorcycle_seq;
    private String member_nm;
    private String motorcycle_owner;
    private String motorcycle_rentStart;
    private String motorcycle_rentEnd;
}
