package com.alazarska.adventofcode2022.day1;

import java.util.ArrayList;
import java.util.List;

public class Day1Solver {

    public Integer solvePartOne(String input) {
        List<String> lines = input.lines().toList();

        List<Integer> caloriesForEachElf = new ArrayList<>();
        int currentElfCalories = 0;

        for (var line : lines) {
            if (!line.isEmpty()) {
                int currentItemCalories = Integer.parseInt(line);
                currentElfCalories += currentItemCalories;
            } else {
                caloriesForEachElf.add(currentElfCalories);
                currentElfCalories = 0;
            }
        }

        caloriesForEachElf.add(currentElfCalories);

        return caloriesForEachElf.stream().mapToInt(Integer::intValue).max().getAsInt();
    }
}
