package com.company;

public class StringScheduleValueVO extends ScheduleValueVO<String> {
    public StringScheduleValueVO(String value) {
        super(ScheduleValueType.STRING, value);
    }
}
