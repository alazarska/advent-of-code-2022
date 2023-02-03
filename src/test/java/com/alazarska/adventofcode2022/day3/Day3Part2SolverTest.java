package com.alazarska.adventofcode2022.day3;

import com.alazarska.adventofcode2022.common.InvalidInputException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class Day3Part2SolverTest {

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

        int result = day3Solver.solvePartTwo(input);

        assertThat(result).isEqualTo(70);
    }

    @Test
    public void shouldThrowExceptionWhenNumberOfLinesIsNotDivisibleByThree() {
        String input = """
                xxx
                yyy
                """;

        assertThatExceptionOfType(InvalidInputException.class)
                .isThrownBy(() -> day3Solver.solvePartTwo(input))
                .withMessageContaining("Input should contain at least 3 lines or number of lines divisible by 3.");
    }
}
