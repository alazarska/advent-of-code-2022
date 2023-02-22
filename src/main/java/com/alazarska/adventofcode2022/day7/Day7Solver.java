package com.alazarska.adventofcode2022.day7;

import com.alazarska.adventofcode2022.common.InvalidInputException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Day7Solver {

    public int solvePartOne(String input, Integer limitOfSizeForDirectory) {
        Directory rootDirectory = parseInputLinesAndReturnRootDirectory(input);

        return visitDirectoriesAndGetItsSumSize(rootDirectory, limitOfSizeForDirectory);
    }

    public int solvePartTwo(String input, Integer totalDeviceSpace, Integer neededUnusedFreeSpace) {
        Directory rootDirectory = parseInputLinesAndReturnRootDirectory(input);

        return getDirectorySizeNeededToDelete(rootDirectory, totalDeviceSpace, neededUnusedFreeSpace);
    }

    private Directory parseInputLinesAndReturnRootDirectory(String input) {
        List<String> allLines = validateInput(input);
        Directory currentDirectory = new Directory("/", null);

        for (String line : allLines.subList(1, allLines.size())) {
            String[] splitLine = line.split(" ");

            if (line.startsWith("$ cd")) {
                if (splitLine.length == 3) {
                    String directoryName = splitLine[2];
                    if (directoryName.equals("..")) {
                        System.out.println("Exiting currentDirectory " + currentDirectory);
                        currentDirectory = currentDirectory.getParent();
                    } else {
                        System.out.println("Entering currentDirectory: " + directoryName);
                        Directory newDirectory = new Directory(directoryName, currentDirectory);
                        currentDirectory.addDirectory(newDirectory);
                        currentDirectory = newDirectory;
                    }
                } else {
                    throw new InvalidInputException("Invalid command: '" + line + "'.");
                }
            } else if (Character.isDigit(line.charAt(0))) {
                try {
                    int size = Integer.parseInt(splitLine[0]);
                    System.out.println("Saving file of size " + size);
                    currentDirectory.addFile(new MyFile(size));
                } catch (NumberFormatException e) {
                    throw new InvalidInputException("Invalid command: '" + line + "'.");
                }
            } else if (line.equals("$ ls") || line.startsWith("dir")) {
                // ignore $ ls and dir *
            } else {
                throw new InvalidInputException("Unknown command: '" + line + "'.");
            }
        }
        return getRootDirectory(currentDirectory);
    }

    private List<String> validateInput(String input) {
        if (input.isEmpty()) {
            throw new InvalidInputException("Input is empty.");
        } else {
            List<String> listOfCommands = input.lines().toList();
            if (listOfCommands.get(0).equals("$ cd /")) {
                return listOfCommands;
            } else {
                throw new InvalidInputException("Unknown command: '" + listOfCommands.get(0) + "'. In first line you should switch to the root directory by command '$ cd /'.");
            }
        }
    }

    private Directory getRootDirectory(Directory currentDirectory) {
        while (currentDirectory.getParent() != null) {
            currentDirectory = currentDirectory.getParent();
        }
        return currentDirectory;
    }

    private Integer getDirectorySizeNeededToDelete(Directory rootDirectory, int totalDeviceSpace, int neededUnusedFreeSpace) {
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

    private int visitDirectoriesAndGetItsSumSize(Directory rootDirectory, Integer limitOfSizeForDirectory) {
        return visitAllDirectoriesAndGetTheirSizes(rootDirectory).stream()
                .mapToInt(Integer::intValue)
                .filter(it -> it <= limitOfSizeForDirectory)
                .sum();
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
