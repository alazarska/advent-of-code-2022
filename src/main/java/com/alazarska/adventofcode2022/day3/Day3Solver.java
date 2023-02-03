package com.alazarska.adventofcode2022.day3;

import com.alazarska.adventofcode2022.common.InvalidInputException;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Day3Solver {

    public int solvePartOne(String input) {
        List<String> lines = extractLines(input);

        return lines.stream()
                .map(line -> {
                    if (line.length() % 2 == 0) {
                        List<String> strings = new ArrayList<>();
                        strings.add(line.substring(0, (line.length()) / 2));
                        strings.add(line.substring((line.length() / 2)));
                        return strings;
                    } else {
                        throw new InvalidInputException("Line length should be divisible by two.");
                    }
                })
                .mapToInt(this::getPointsForCharacterOccurringInAllStrings)
                .sum();
    }

    public int solvePartTwo(String input) {
        List<String> lines = extractLines(input);
        List<List<String>> listOfLines = Lists.partition(lines, 3);

        return listOfLines.stream()
                .map(list -> {
                    if (list.size() == 3) {
                        return list;
                    } else {
                        throw new InvalidInputException("Input should contain at least 3 lines or number of lines divisible by 3.");
                    }
                })
                .mapToInt(this::getPointsForCharacterOccurringInAllStrings)
                .sum();
    }

    private List<String> extractLines(String input) {
        if (input.isEmpty()) {
            throw new InvalidInputException("Input is empty.");
        } else {
            List<String> lines = input.lines().toList();
            for (int i = 0; i < lines.size(); i++) {
                if (!lines.get(i).matches("[a-zA-Z]+")) {
                    throw new InvalidInputException("Invalid characters in line " + (i + 1) + ". Proper characters: a-z, A-Z.");
                }
            }
            return lines;
        }
    }

    private int getPointsForCharacterOccurringInAllStrings(List<String> strings) {
        Optional<Character> foundCharacter = strings.get(0).chars()
                .mapToObj(it -> (char) it)
                .filter(character -> strings.stream()
                        .allMatch(string -> string.contains(String.valueOf(character))))
                .findFirst();

        if (foundCharacter.isPresent()) {
            return getPointsForCharacter(foundCharacter.get());
        } else {
            throw new InvalidInputException("Character appearing in all elements not found.");
        }
    }

    public int getPointsForCharacter(char x) {
        if (x >= 65 && x <= 90) {
            return x - 64 + 26;
        } else if (x >= 97 && x <= 122) {
            return x - 96;
        } else {
            throw new InvalidInputException("Points for character: " + x + " not found. Proper characters: a-z, A-Z.");
        }
    }
}
