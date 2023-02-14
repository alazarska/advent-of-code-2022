package com.alazarska.adventofcode2022.day6;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Day6Part2SolverTest {

    private final Day6Solver day6Solver = new Day6Solver();

    @Test
    public void shouldReturnProperResultForExampleInputFromTaskDescription() {
        String input = "mjqjpqmgbljsphdztnvjfqwrcgsmlb";

        int result = day6Solver.solvePartTwo(input);

        assertThat(result).isEqualTo(19);
    }
}
