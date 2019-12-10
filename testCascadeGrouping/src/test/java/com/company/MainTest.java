package com.company;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MainTest {

    private List<ScheduleVO> scheduleVOS = new ArrayList<>();

    private final int MATRIX_SIZE = 24;

    @BeforeEach
    public void setUp() {
        int offset = 3;
        int salt1 = 1;
        int salt2 = 1;
        int salt3 = 1;

        scheduleVOS.add(generateScheduleVO(offset++, salt1, salt2, salt3));
        scheduleVOS.add(generateScheduleVO(offset++, salt1, salt2, salt3));
        scheduleVOS.add(generateScheduleVO(offset++, salt1, salt2, salt3));

        salt3++;
        scheduleVOS.add(generateScheduleVO(offset++, salt1, salt2, salt3));
        scheduleVOS.add(generateScheduleVO(offset++, salt1, salt2, salt3));
        scheduleVOS.add(generateScheduleVO(offset++, salt1, salt2, salt3));

        salt2++;
        salt3--;
        scheduleVOS.add(generateScheduleVO(offset++, salt1, salt2, salt3));
        scheduleVOS.add(generateScheduleVO(offset++, salt1, salt2, salt3));
        scheduleVOS.add(generateScheduleVO(offset++, salt1, salt2, salt3));

        salt3++;
        scheduleVOS.add(generateScheduleVO(offset++, salt1, salt2, salt3));
        scheduleVOS.add(generateScheduleVO(offset++, salt1, salt2, salt3));
        scheduleVOS.add(generateScheduleVO(offset++, salt1, salt2, salt3));

        salt1++;
        salt2++;
        salt3--;
        scheduleVOS.add(generateScheduleVO(offset++, salt1, salt2, salt3));
        scheduleVOS.add(generateScheduleVO(offset++, salt1, salt2, salt3));
        scheduleVOS.add(generateScheduleVO(offset++, salt1, salt2, salt3));

        salt3++;
        scheduleVOS.add(generateScheduleVO(offset++, salt1, salt2, salt3));
        scheduleVOS.add(generateScheduleVO(offset++, salt1, salt2, salt3));
        scheduleVOS.add(generateScheduleVO(offset++, salt1, salt2, salt3));

        salt2++;
        salt3++;
        scheduleVOS.add(generateScheduleVO(offset++, salt1, salt2, salt3));
        scheduleVOS.add(generateScheduleVO(offset++, salt1, salt2, salt3));
        scheduleVOS.add(generateScheduleVO(offset++, salt1, salt2, salt3));

        salt3--;
        scheduleVOS.add(generateScheduleVO(offset++, salt1, salt2, salt3));
        scheduleVOS.add(generateScheduleVO(offset++, salt1, salt2, salt3));
        scheduleVOS.add(generateScheduleVO(offset, salt1, salt2, salt3));
    }

    private ScheduleVO generateScheduleVO(int offset, int salt1, int salt2, int salt3) {
        List<ScheduleValueVO> values = Stream.concat(
                getMetaData(salt1, salt2, salt3),
                generateNumberValues(offset))
                .collect(Collectors.toList());
        return new ScheduleVO(values);
    }

    private Stream<StringScheduleValueVO> getMetaData(int salt1, int salt2, int salt3) {
        return Stream.of(
                new StringScheduleValueVO(GroupingKey.FIRST.getLabel() + salt1),
                new StringScheduleValueVO(GroupingKey.SECOND.getLabel() + salt2),
                new StringScheduleValueVO(GroupingKey.THIRD.getLabel() + salt3));
    }

    private Stream<NumberScheduleValueVO> generateNumberValues(int offset) {
        return IntStream.generate(() -> offset).limit(offset + MATRIX_SIZE)
                .mapToObj(BigDecimal::new)
                .map(NumberScheduleValueVO::new);
    }


    /**
     *
     *  Group1 | Group1_1 | Group1_1_1 | [5,4,6,7...matrix_size] - aggregated column sum
     *                                  --2,1,3,5...---
     *                                  --3,3,3,2...---
     * .....
     * GroupN | GroupN_M | GroupN_M_K | [15,14, 26, 37...matrix_size]
     *                                  --7, 1,  3,  5...---
     *                                  --8, 13, 23, 32...---
     *
      */
    @Test
    @DisplayName("Test cascade grouping with aggregation")
    public void testAggregation() {
        Map<String, Map<String, Map<String, List<BigDecimal>>>> groupedAggregatedScheduleVOs = ScheduleServiceImpl.getGroupedAggregatedScheduleVOs(scheduleVOS);
        assertNotNull(groupedAggregatedScheduleVOs);
        assertEquals(2, groupedAggregatedScheduleVOs.size());
        assertNotNull(groupedAggregatedScheduleVOs.get(GroupingKey.FIRST.getLabel() + 1));
        assertNotNull(groupedAggregatedScheduleVOs.get(GroupingKey.FIRST.getLabel() + 1).get(GroupingKey.SECOND.getLabel() + 1));
        assertNotNull(groupedAggregatedScheduleVOs.get(GroupingKey.FIRST.getLabel() + 1).get(GroupingKey.SECOND.getLabel() + 1).get(GroupingKey.THIRD.getLabel() + 1));
        assertEquals(1, groupedAggregatedScheduleVOs.get(GroupingKey.FIRST.getLabel() + 1).get(GroupingKey.SECOND.getLabel() + 1).get(GroupingKey.THIRD.getLabel() + 1).size());
        System.out.println(groupedAggregatedScheduleVOs);
    }

}