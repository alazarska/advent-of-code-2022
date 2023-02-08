package com.alazarska.adventofcode2022.day4;

import com.alazarska.adventofcode2022.common.InvalidInputException;
import com.alazarska.adventofcode2022.common.Pair;
import org.apache.commons.lang3.Range;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Day4InputParser {

    public Stream<Pair<Range<Integer>, Range<Integer>>> parsePairsOfRanges(String input) {
        if (input.isEmpty()) {
            throw new InvalidInputException("Input is empty.");
        } else {
            return input.lines()
                    .map(this::parsePairOfRanges);
        }
    }

    private Pair<Range<Integer>, Range<Integer>> parsePairOfRanges(String line) {
        List<String> strings = Arrays.asList(line.split(","));

        if (strings.size() == 2) {
            List<Range<Integer>> ranges = strings.stream()
                    .map(this::parseRange)
                    .toList();
            return new Pair<>(ranges.get(0), ranges.get(1));
        } else {
            throw new InvalidInputException("Invalid input: " + line + ". Correct line should contain two ranges, separated by comma. Example correct line: 1-10,1-1.");
        }
    }

    private Range<Integer> parseRange(String string) {
        try {
            List<String> borderValuesForRange = Arrays.stream(string.split("-")).toList();

            try {
                int beginningNumber = Integer.parseInt(borderValuesForRange.get(0));
                int endNumber = Integer.parseInt(borderValuesForRange.get(1));

                return Range.between(beginningNumber, endNumber);
            } catch (NumberFormatException e) {
                throw new InvalidInputException("Invalid input: " + string +
                        ". Correct range should consist of numbers and should be separated by dash. Example correct range: 1-10.");
            }
        } catch (InvalidInputException e) {
            throw new InvalidInputException("Invalid input: " + string +
                    ". Correct range should consist of numbers and should be separated by dash. Example correct range: 1-10.");
        }
    }
}
