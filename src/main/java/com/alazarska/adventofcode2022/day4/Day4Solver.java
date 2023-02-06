package com.alazarska.adventofcode2022.day4;

import com.alazarska.adventofcode2022.common.InvalidInputException;
import com.alazarska.adventofcode2022.common.Pair;
import org.apache.commons.lang3.Range;

import java.util.Arrays;
import java.util.List;

public class Day4Solver {

    public int solvePartOne(String input) {
        List<Pair<Range<Integer>, Range<Integer>>> pairsOfRanges = extractPairs(input);

        return pairsOfRanges.stream()
                .mapToInt(pairs -> {
                    if (containsNumbersFromOtherRange(pairs)) {
                        return 1;
                    } else {
                        return 0;
                    }
                })
                .sum();
    }

    public int solvePartTwo(String input) {
        List<Pair<Range<Integer>, Range<Integer>>> pairsOfRanges = extractPairs(input);

        return pairsOfRanges.stream()
                .mapToInt(pairs -> {
                    if (overlapNumbersFromOtherRange(pairs)) {
                        return 1;
                    } else {
                        return 0;
                    }
                })
                .sum();
    }

    private List<Pair<Range<Integer>, Range<Integer>>> extractPairs(String input) {
        if (input.isEmpty()) {
            throw new InvalidInputException("Input is empty.");
        } else {
            return input.lines()
                    .map(string -> {
                        List<String> pairs = Arrays.asList(string.split(","));

                        if (pairs.size() == 2) {
                            List<String> firstPair = Arrays.stream(pairs.get(0).split("-")).toList();
                            List<String> secondPair = Arrays.stream(pairs.get(1).split("-")).toList();

                            if (firstPair.size() == 2 && secondPair.size() == 2) {
                                try {
                                    return new Pair<>(
                                            Range.between(Integer.parseInt(firstPair.get(0)), Integer.parseInt(firstPair.get(1))),
                                            Range.between(Integer.parseInt(secondPair.get(0)), Integer.parseInt(secondPair.get(1))));
                                } catch (NumberFormatException e) {
                                    throw new InvalidInputException("Invalid input: " + string + ". Ranges contain data other than numbers.");
                                }
                            } else {
                                throw new InvalidInputException("Invalid input: " + string + ". Correct range should by separated by dash.");
                            }
                        } else {
                            throw new InvalidInputException("Invalid input: " + string + ". Correct line should contain two ranges, separated by comma. Example correct line: 1-10,1-1.");
                        }
                    })
                    .toList();
        }
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
