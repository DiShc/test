package com.company;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@UtilityClass
public class ScheduleServiceImpl {

    public static Map<String, Map<String, Map<String, List<BigDecimal>>>> getGroupedAggregatedScheduleVOs(List<ScheduleVO> scheduleVOS){
//        TODO: implement aggregation with triple-grouping
        return null;
    }
}
