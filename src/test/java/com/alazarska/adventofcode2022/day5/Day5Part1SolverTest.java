package com.alazarska.adventofcode2022.day5;

import com.alazarska.adventofcode2022.common.InvalidInputException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

public class Day5Part1SolverTest {

    Day5Solver day5Solver = new Day5Solver();

    @Test
    public void shouldReturnProperResultForExampleInputFromTaskDescription() {
        List<CratesStack> stacks = Arrays.asList(
                new CratesStack('Z', 'N'),
                new CratesStack('M', 'C', 'D'),
                new CratesStack('P')
        );

        String procedures = """
                move 1 from 2 to 1
                move 3 from 1 to 3
                move 2 from 2 to 1
                move 1 from 1 to 2
                """;

        String result = day5Solver.solvePartOne(stacks, procedures);
        assertThat(result).isEqualTo("CMZ");
    }

    @Test
    public void shouldThrowExceptionWhenProcedureIsInvalid() {
        List<CratesStack> stacks = new ArrayList<>();
        stacks.add(new CratesStack('Z', 'N'));

        String procedures = """
                # a from 2 to 1
                """;

        assertThatExceptionOfType(InvalidInputException.class)
                .isThrownBy(() -> day5Solver.solvePartOne(stacks, procedures))
                .withMessageContaining("Invalid procedure: # a from 2 to 1. Example correct procedure: move 2 from 2 to 1.");
    }

    @Test
    public void shouldThrowExceptionWhenProcedureIsEmpty() {
        List<CratesStack> stacks = new ArrayList<>();
        stacks.add(new CratesStack('Z', 'N'));

        String procedures = """
                                
                """;

        assertThatExceptionOfType(InvalidInputException.class)
                .isThrownBy(() -> day5Solver.solvePartOne(stacks, procedures))
                .withMessageContaining("Procedure is empty. Enter at least one valid procedure.");
    }

    @Test
    public void shouldThrowExceptionWhenStackListIsEmpty() {
        List<CratesStack> stacks = new ArrayList<>();

        String procedures = """
                move 1 from 2 to 1
                """;

        assertThatExceptionOfType(InvalidInputException.class)
                .isThrownBy(() -> day5Solver.solvePartOne(stacks, procedures))
                .withMessageContaining("Stack list is empty.");
    }
}
