package com.alazarska.adventofcode2022.day1;

import com.alazarska.adventofcode2022.common.InvalidInputException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

class Day1Part1SolverTest {

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

        Integer result = day1Solver.solvePartOne(input);

        assertThat(result).isEqualTo(24000);
    }

    @Test
    public void shouldReturnProperResultForSingleGroup() {
        String input = """
                1
                2
                3
                """;

        Integer result = day1Solver.solvePartOne(input);

        assertThat(result).isEqualTo(6);
    }

    @Test
    public void shouldReturnProperResultForBiggestGroupAtTheBeginning() {
        String input = """
                2
                2
                2
                                     
                1
                        
                2
                """;

        Integer result = day1Solver.solvePartOne(input);

        assertThat(result).isEqualTo(6);
    }

    @Test
    public void shouldReturnProperResultForBiggestGroupAtTheEnd() {
        String input = """
                1
                2
                2
                                     
                3
                               
                9
                """;

        Integer result = day1Solver.solvePartOne(input);

        assertThat(result).isEqualTo(9);
    }

    @Test
    public void shouldReturnProperResultForInputContainingEmptyGroup() {
        String input = """
                1
                2
                   
                                     
                4
                """;

        Integer result = day1Solver.solvePartOne(input);

        assertThat(result).isEqualTo(4);
    }

    @Test
    public void shouldReturnExceptionWhenInputStringIsLessThanOneGroup() {
        String input = "";

        assertThatExceptionOfType(InvalidInputException.class)
                .isThrownBy(() -> {
                    day1Solver.solvePartOne(input);
                }).withMessageContaining("Input is empty.");
    }

    @Test
    public void shouldReturnExceptionWhenInputStringIsNotNumber() {
        String input = "abc";

        assertThatExceptionOfType(InvalidInputException.class)
                .isThrownBy(() -> {
                    day1Solver.solvePartOne(input);
                }).withMessageContaining("One of calories value is not proper number.");
    }
}
