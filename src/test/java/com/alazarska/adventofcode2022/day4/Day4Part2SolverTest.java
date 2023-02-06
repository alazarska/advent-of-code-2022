package com.alazarska.adventofcode2022.day4;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Day4Part2SolverTest {

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

        int result = day4Solver.solvePartTwo(input);

        assertThat(result).isEqualTo(4);
    }
}
