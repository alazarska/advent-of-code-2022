package com.alazarska.adventofcode2022.day1;

import com.alazarska.adventofcode2022.common.InvalidInputException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day1Solver {

    public Integer solvePartOne(String input) {
        List<Integer> listOfCaloriesForEachElf = extractElfCalories(input);
        return listOfCaloriesForEachElf.stream().mapToInt(Integer::intValue).max().getAsInt();
    }

    public Integer solvePartTwo(String input) {
        List<Integer> listOfCaloriesForEachElf = extractElfCalories(input);
        if (listOfCaloriesForEachElf.size() < 3) {
            throw new InvalidInputException("List of Elves calories is too short. You have to insert at least data for 3 Elves.");
        } else {
            List<Integer> sortedListOfCalories = listOfCaloriesForEachElf.stream().sorted(Collections.reverseOrder()).toList();
            return sortedListOfCalories.subList(0, 3).stream().mapToInt(Integer::intValue).sum();
        }
    }

    public List<Integer> extractElfCalories(String input) {
        if (input.isEmpty()) {
            throw new InvalidInputException("Input is empty.");
        }

        List<String> lines = input.lines().toList();

        List<Integer> caloriesForEachElf = new ArrayList<>();
        int currentElfCalories = 0;

        for (var line : lines) {
            if (!line.isEmpty()) {
                try {
                    int currentItemCalories = Integer.parseInt(line);
                    currentElfCalories += currentItemCalories;
                } catch (NumberFormatException e) {
                    throw new InvalidInputException("One of calories value is not proper number.", e);
                }
            } else {
                caloriesForEachElf.add(currentElfCalories);
                currentElfCalories = 0;
            }
        }

        caloriesForEachElf.add(currentElfCalories);

        return caloriesForEachElf;
    }
}
