package com.alazarska.adventofcode2022.day2;

import com.alazarska.adventofcode2022.common.InvalidInputException;
import com.alazarska.adventofcode2022.common.Pair;

import java.util.stream.Stream;

public class Day2Solver {

    public int solvePartOne(String input) {
        return extractRounds(input)
                .map(pair -> new Pair<>(mapOpponentShape(pair.getValue1()), mapYourShape(pair.getValue2())))
                .mapToInt(pair -> {
                    Shape mappedOpponentShape = pair.getValue2();
                    Shape mappedYourShape = pair.getValue1();

                    int pointsForShape = getPointsForShape(mappedYourShape);

                    if (mappedOpponentShape.equals(mappedYourShape)) {
                        return pointsForShape + 3;
                    } else if (mappedOpponentShape.getWinner().equals(mappedYourShape)) {
                        return pointsForShape + 6;
                    } else {
                        return pointsForShape;
                    }
                })
                .sum();
    }

    public int solvePartTwo(String input) {
        return extractRounds(input)
                .map(pair -> new Pair<>(mapOpponentShape(pair.getValue1()), mapResult(pair.getValue2())))
                .mapToInt(pair -> {
                    Shape mappedOpponentShape = pair.getValue1();
                    Result mappedResult = pair.getValue2();

                    if (mappedResult.equals(Result.DRAW)) {
                        return 3 + getPointsForShape(mappedOpponentShape);

                    } else if (mappedResult.equals(Result.WIN)) {
                        return 6 + getPointsForShape(mappedOpponentShape.getWinner());

                    } else {
                        return getPointsForShape(mappedOpponentShape.getLoser());
                    }
                })
                .sum();
    }

    private Stream<Pair<Character, Character>> extractRounds(String input) {
        if (input.isEmpty()) {
            throw new InvalidInputException("Input is empty.");
        } else {
            return input.lines()
                    .map(line -> {
                        if (line.length() == 3) {
                            if (line.charAt(1) == ' ') {
                                return new Pair<>(line.charAt(0), line.charAt(2));
                            } else {
                                throw new InvalidInputException("Proper line should contain space between two characters.");
                            }
                        } else {
                            throw new InvalidInputException("Proper line should contains 3 characters.");
                        }
                    });
        }
    }

    private Shape mapYourShape(char character) {
        return switch (character) {
            case 'X' -> Shape.ROCK;
            case 'Y' -> Shape.PAPER;
            case 'Z' -> Shape.SCISSORS;
            default ->
                    throw new InvalidInputException("Your shape has bad value: " + character + ". Proper characters: X, Y, Z.");
        };
    }

    private Shape mapOpponentShape(char character) {
        return switch (character) {
            case 'A' -> Shape.ROCK;
            case 'B' -> Shape.PAPER;
            case 'C' -> Shape.SCISSORS;
            default ->
                    throw new InvalidInputException("Opponent shape has bad value: " + character + ". Proper characters: A, B, C.");
        };
    }

    private Result mapResult(char character) {
        return switch (character) {
            case 'X' -> Result.LOSS;
            case 'Y' -> Result.DRAW;
            case 'Z' -> Result.WIN;
            default ->
                    throw new InvalidInputException("Your result shape has bad value: " + character + ". Proper characters: X, Y, Z.");
        };
    }

    private static int getPointsForShape(Shape yourShape) {
        int points = 0;
        return switch ((yourShape)) {
            case ROCK -> points + 1;
            case PAPER -> points + 2;
            case SCISSORS -> points + 3;
        };
    }
}
