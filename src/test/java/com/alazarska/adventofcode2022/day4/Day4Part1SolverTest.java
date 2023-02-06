package com.alazarska.adventofcode2022.day4;

import com.alazarska.adventofcode2022.common.InvalidInputException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

public class Day4Part1SolverTest {

    private final Day4Solver day4Solver = new Day4Solver();

    @Test
    public void shouldReturnProperResultForExampleInputFromTaskDescription() {
        String input = """
                2-4,6-8
                2-3,4-5
                5-7,7-9
                2-8,3-7
                6-6,4-6
                2-6,4-8
                """;

        int result = day4Solver.solvePartOne(input);

        assertThat(result).isEqualTo(2);
    }

    @Test
    public void shouldThrowExceptionWhenInputIsEmpty() {
        String input = "";

        assertThatExceptionOfType(InvalidInputException.class)
                .isThrownBy(() -> day4Solver.solvePartOne(input))
                .withMessageContaining("Input is empty.");
    }

    @Test
    public void shouldThrowExceptionWhenInputLineLengthIsInvalid() {
        String input = "2-4";

        assertThatExceptionOfType(InvalidInputException.class)
                .isThrownBy(() -> day4Solver.solvePartOne(input))
                .withMessageContaining("Invalid input: 2-4. Correct line should contain two ranges, separated by comma. Example correct line: 1-10,1-1.");
    }

    @Test
    public void shouldThrowExceptionWhenInputRangeSeparatorIsInvalid() {
        String input = "2.4,11r2";

        assertThatExceptionOfType(InvalidInputException.class)
                .isThrownBy(() -> day4Solver.solvePartOne(input))
                .withMessageContaining("Invalid input: 2.4,11r2. Correct range should by separated by dash.");
    }

    @Test
    public void shouldThrowExceptionWhenInputRangesContainDataOtherThanNumbers() {
        String input = "a-$,1-*";

        assertThatExceptionOfType(InvalidInputException.class)
                .isThrownBy(() -> day4Solver.solvePartOne(input))
                .withMessageContaining("Invalid input: a-$,1-*. Ranges contain data other than numbers.");
    }
}
