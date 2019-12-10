package com.company;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GroupingKey {
    FIRST("1st hierarchy level"),
    SECOND("2nd hierarchy level"),
    THIRD("3rd hierarchy level");
    private String label;
}
