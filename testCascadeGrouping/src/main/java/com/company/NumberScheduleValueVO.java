package com.company;

import java.math.BigDecimal;


public class NumberScheduleValueVO extends ScheduleValueVO<BigDecimal> {
    public NumberScheduleValueVO(BigDecimal value) {
        super(ScheduleValueType.NUMBER, value);
    }
}
