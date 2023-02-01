package com.alazarska.adventofcode2022.day1;

import com.alazarska.adventofcode2022.common.InvalidInputException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

class Day1Part2SolverTest {

    private final Day1Solver day1Solver = new Day1Solver();

    @Test
    public void shouldReturnProperResultForExampleInputFromTaskDescription() {
        String input = """
                1000
                2000
                3000
                        
                4000
                        
                5000
                6000
                        
                7000
                8000
                9000
                        
                10000
                """;

        Integer result = day1Solver.solvePartTwo(input);

        assertThat(result).isEqualTo(45000);
    }

    @Test
    public void shouldReturnExceptionWhenInputStringIsLessThanThreeGroups() {
        String input = """
                1
                """;

        assertThatExceptionOfType(InvalidInputException.class)
                .isThrownBy(() -> {
                    day1Solver.solvePartTwo(input);
                }).withMessageContaining("List of Elves calories is too short. You have to insert at least data for 3 Elves.");
    }
}
