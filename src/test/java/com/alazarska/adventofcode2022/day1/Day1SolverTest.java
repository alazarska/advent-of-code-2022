package com.alazarska.adventofcode2022.day1;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day1SolverTest {

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
}
