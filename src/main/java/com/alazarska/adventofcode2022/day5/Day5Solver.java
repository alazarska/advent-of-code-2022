package com.alazarska.adventofcode2022.day5;

import com.alazarska.adventofcode2022.common.InvalidInputException;

import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day5Solver {

    private static final Pattern procedurePattern =
            Pattern.compile("move (?<elementsToMove>\\d+) from (?<actualStack>\\d+) to (?<targetStack>\\d+)");

    public String solvePartOne(List<CratesStack> stacks, String procedures) {
        parseProcedures(procedures)
                .forEach(procedure -> rearrangeCrates(procedure, stacks, false));
        return getTopCratesCharacters(stacks);
    }

    public String solvePartTwo(List<CratesStack> stacks, String procedures) {
        parseProcedures(procedures)
                .forEach(procedure -> rearrangeCrates(procedure, stacks, true));
        return getTopCratesCharacters(stacks);
    }

    private Stream<Procedure> parseProcedures(String procedures) {
        return procedures.lines()
                .map(this::parseProcedure);
    }

    private Procedure parseProcedure(String procedure) {
        if (procedure.isEmpty()) {
            throw new InvalidInputException("Procedure is empty. Enter at least one valid procedure.");
        }

        var matcher = procedurePattern.matcher(procedure);
        matcher.find();
        try {
            return new Procedure(
                    Integer.parseInt(matcher.group("elementsToMove")),
                    Integer.parseInt(matcher.group("actualStack")),
                    Integer.parseInt(matcher.group("targetStack")));
        } catch (IllegalStateException e) {
            throw new InvalidInputException("Invalid procedure: " + procedure + ". Example correct procedure: move 2 from 2 to 1.");
        }
    }

    private void rearrangeCrates(Procedure procedure, List<CratesStack> stacks, Boolean reverseMovedElements) {
        if (stacks.isEmpty()) {
            throw new InvalidInputException("Stack list is empty.");
        }
        CratesStack actualStack = stacks.get(procedure.actualStackNumber() - 1);
        CratesStack targetStack = stacks.get(procedure.targetStackNumber() - 1);

        List<Character> elementsToMove = actualStack.takeCrates(procedure.elementsToMove());

        if (reverseMovedElements.equals(true)) {
            Collections.reverse(elementsToMove);
            targetStack.pushCrates(elementsToMove);
        } else {
            targetStack.pushCrates(elementsToMove);
        }
    }

    private static String getTopCratesCharacters(List<CratesStack> stacks) {
        return stacks.stream()
                .map(stack -> stack.getCrates().get(stack.size() - 1))
                .map(ch -> String.valueOf(ch).toUpperCase())
                .collect(Collectors.joining());
    }
}
