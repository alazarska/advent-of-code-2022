package com.alazarska.adventofcode2022.day3;

import com.alazarska.adventofcode2022.common.InvalidInputException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class Day3Part1SolverTest {

    private final Day3Solver day3Solver = new Day3Solver();

    @Test
    public void shouldReturnProperResultForExampleInputFromTaskDescription() {
        String input = """
                vJrwpWtwJgWrhcsFMMfFFhFp
                jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
                PmmdzqPrVvPwwTWBwg
                wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
                ttgJtRGJQctTZtZT
                CrZsJsPPZsGzwwsLwLmpwMDw
                """;

        int result = day3Solver.solvePartOne(input);

        assertThat(result).isEqualTo(157);
    }

    @Test
    public void shouldThrowExceptionWhenInputIsEmpty() {
        String input = "";

        assertThatExceptionOfType(InvalidInputException.class)
                .isThrownBy(() -> day3Solver.solvePartOne(input))
                .withMessageContaining("Input is empty.");
    }

    @Test
    public void shouldThrowExceptionWhenLineLengthIsNotDivisibleByTwo() {
        String input = """
                xxzzt
                """;

        assertThatExceptionOfType(InvalidInputException.class)
                .isThrownBy(() -> day3Solver.solvePartOne(input))
                .withMessageContaining("Line length should be divisible by two.");
    }

    @Test
    public void shouldThrowExceptionWhenThereIsNoDuplicatedCharacterFound() {
        String input = """
                xxxzzz
                """;

        assertThatExceptionOfType(InvalidInputException.class)
                .isThrownBy(() -> day3Solver.solvePartOne(input))
                .withMessageContaining("Character appearing in all elements not found.");
    }

    @Test
    public void shouldThrowExceptionWhenCharactersAreNotLetters() {
        String input = """
                aa
                1a
                """;

        assertThatExceptionOfType(InvalidInputException.class)
                .isThrownBy(() -> day3Solver.solvePartOne(input))
                .withMessageContaining("Invalid characters in line 2. Proper characters: a-z, A-Z.");
    }
}
