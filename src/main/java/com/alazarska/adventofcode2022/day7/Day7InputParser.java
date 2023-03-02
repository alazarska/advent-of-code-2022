package com.alazarska.adventofcode2022.day7;

import com.alazarska.adventofcode2022.common.InvalidInputException;

import java.util.List;

public class Day7InputParser {

    public Directory parseInputLinesAndReturnRootDirectory(String input) {
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
                int size;
                try {
                    size = Integer.parseInt(splitLine[0]);
                } catch (NumberFormatException e) {
                    throw new InvalidInputException("Invalid command: '" + line + "'.");
                }
                System.out.println("Saving file of size " + size);
                currentDirectory.addFile(new MyFile(size));
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
}
