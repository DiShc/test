package com.company;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * values should contains:
 *  -   3 String parameters ({@link GroupingKey})
 *  -   24 BigDecimal numbers
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleVO {
    private List<? extends ScheduleValueVO> values;

}
