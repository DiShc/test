package com.company;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@UtilityClass
public class ScheduleServiceImpl {

    public static Map<String, Map<String, Map<String, List<BigDecimal>>>> getGroupedAggregatedScheduleVOs(List<ScheduleVO> scheduleVOS) {
//        TODO: implement aggregation with triple-grouping
        scheduleVOS.stream()
                .collect(Collectors.groupingBy(scheduleVO -> findValue(scheduleVO, GroupingKey.FIRST)));

        return null;
    }

    private ScheduleValueVO findValue(ScheduleVO scheduleVO, GroupingKey groupingKey) {
        return scheduleVO.getValues().stream()
                .filter(v -> v instanceof StringScheduleValueVO && ((String) v.getValue()).contains(groupingKey.getLabel()))
                .findFirst()
                .orElse(null);
    }
}
