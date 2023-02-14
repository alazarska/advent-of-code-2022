package com.alazarska.adventofcode2022.day6;

import com.alazarska.adventofcode2022.common.InvalidInputException;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class Day6Solver {

    public int solvePartOne(String input) {
        return findLastIndexOfUniqueMarkerWithGivenLength(validateString(input), 4);
    }

    public int solvePartTwo(String input) {
        return findLastIndexOfUniqueMarkerWithGivenLength(validateString(input), 14);
    }

    private String validateString(String input) {
        if (input.isEmpty()) {
            throw new InvalidInputException("Input is empty.");
        } else {
            if (!input.matches("[a-zA-Z]+")) {
                throw new InvalidInputException("Invalid characters in input. Proper characters: a-z, A-Z.");
            } else {
                return input;
            }
        }
    }

    private int findLastIndexOfUniqueMarkerWithGivenLength(String string, int lengthOfMarker) {
        OptionalInt firstIndexOfMarker = IntStream.range(0, string.length() - lengthOfMarker + 1)
                .filter((i) -> {
                    String marker = string.substring(i, i + lengthOfMarker);
                    return checkIfCharactersAreUnique(marker);
                })
                .findFirst();

        if (firstIndexOfMarker.isPresent()) {
            return firstIndexOfMarker.getAsInt() + lengthOfMarker;
        } else {
            throw new InvalidInputException("Marker with unique characters not found.");
        }
    }

    private boolean checkIfCharactersAreUnique(String str) {
        char[] chars = str.toCharArray();
        Arrays.sort(str.toCharArray());
        for (int i = 0; i < chars.length - 1; i++) {
            if (chars[i] == chars[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
