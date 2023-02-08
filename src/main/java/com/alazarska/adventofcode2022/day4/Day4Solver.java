package com.alazarska.adventofcode2022.day4;

import com.alazarska.adventofcode2022.common.Pair;
import org.apache.commons.lang3.Range;

public class Day4Solver {

    private final Day4InputParser inputParser = new Day4InputParser();

    public int solvePartOne(String input) {
        return inputParser.parsePairsOfRanges(input)
                .mapToInt(pairs -> containsNumbersFromOtherRange(pairs) ? 1 : 0)
                .sum();
    }

    public int solvePartTwo(String input) {
        return inputParser.parsePairsOfRanges(input)
                .mapToInt(pairs -> overlapNumbersFromOtherRange(pairs) ? 1 : 0)
                .sum();
    }

    private boolean containsNumbersFromOtherRange(Pair<Range<Integer>, Range<Integer>> pairOfRanges) {
        Range<Integer> firstRange = pairOfRanges.getValue1();
        Range<Integer> secondRange = pairOfRanges.getValue2();

        return firstRange.containsRange(secondRange) || secondRange.containsRange(firstRange);
    }

    private boolean overlapNumbersFromOtherRange(Pair<Range<Integer>, Range<Integer>> pairOfRanges) {
        Range<Integer> firstRange = pairOfRanges.getValue1();
        Range<Integer> secondRange = pairOfRanges.getValue2();

        return firstRange.isOverlappedBy(secondRange);
    }
}
