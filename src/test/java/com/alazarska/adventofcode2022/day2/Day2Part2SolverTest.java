package com.alazarska.adventofcode2022.day2;

import com.alazarska.adventofcode2022.common.InvalidInputException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

public class Day2Part2SolverTest {

    private final Day2Solver day2Solver = new Day2Solver();

    @Test
    public void shouldReturnProperResultForExampleInputFromTaskDescription() {
        String input = """
                A Y
                B X
                C Z
                """;

        Integer result = day2Solver.solvePartTwo(input);

        assertThat(result).isEqualTo(12);
    }

    @Test
    public void shouldThrowExceptionWhenInputContainsIncorrectCharacterInFirstColumn() {
        String input = """
                Z Y
                """;

        assertThatExceptionOfType(InvalidInputException.class)
                .isThrownBy(() -> day2Solver.solvePartTwo(input))
                .withMessageContaining("Opponent shape has bad value: Z. Proper characters: A, B, C.");
    }

    @Test
    public void shouldThrowExceptionWhenInputContainsIncorrectCharacterInSecondColumn() {
        String input = """
                A A
                """;

        assertThatExceptionOfType(InvalidInputException.class)
                .isThrownBy(() -> day2Solver.solvePartTwo(input))
                .withMessageContaining("Your result shape has bad value: A. Proper characters: X, Y, Z.");
    }
}
