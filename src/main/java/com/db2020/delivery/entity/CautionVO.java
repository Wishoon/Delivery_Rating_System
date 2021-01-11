package com.db2020.delivery.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
public class CautionVO {
    private String caution_createAt;
    private String caution_reason;
}
