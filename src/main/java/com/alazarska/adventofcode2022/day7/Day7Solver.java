package com.alazarska.adventofcode2022.day7;

import com.alazarska.adventofcode2022.common.InvalidInputException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Day7Solver {

    private final Day7InputParser inputParser = new Day7InputParser();

    public int solvePartOne(String input, Integer limitOfSizeForDirectory) {
        Directory rootDirectory = inputParser.parseInputLinesAndReturnRootDirectory(input);

        return visitAllDirectoriesAndGetTheirSizes(rootDirectory).stream()
                .mapToInt(Integer::intValue)
                .filter(it -> it <= limitOfSizeForDirectory)
                .sum();
    }

    public int solvePartTwo(String input, Integer totalDeviceSpace, Integer neededUnusedFreeSpace) {
        Directory rootDirectory = inputParser.parseInputLinesAndReturnRootDirectory(input);

        int actualFreeSpace = totalDeviceSpace - rootDirectory.getSize();
        int neededSpace = neededUnusedFreeSpace - actualFreeSpace;

        List<Integer> listOfDirectorySizes = visitAllDirectoriesAndGetTheirSizes(rootDirectory);

        Optional<Integer> size = listOfDirectorySizes.stream()
                .sorted()
                .filter(it -> it >= neededSpace)
                .findFirst();

        if (size.isPresent()) {
            return size.get();
        } else {
            throw new InvalidInputException("Directory with at least " + neededSpace + " size not found.");
        }
    }

    private List<Integer> visitAllDirectoriesAndGetTheirSizes(Directory directory) {
        return visitAllDirectoriesAndGetTheirSizes(directory, new ArrayList<>());
    }

    private List<Integer> visitAllDirectoriesAndGetTheirSizes(Directory directory, List<Integer> listOfDirectorySizes) {
        if (directory.getSize() > 0) {
            listOfDirectorySizes.add(directory.getSize());
        }

        for (Directory it : directory.getDirectoryList()) {
            visitAllDirectoriesAndGetTheirSizes(it, listOfDirectorySizes);
        }

        return listOfDirectorySizes;
    }
}
