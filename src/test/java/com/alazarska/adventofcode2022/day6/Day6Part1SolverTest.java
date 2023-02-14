package com.alazarska.adventofcode2022.day6;

import com.alazarska.adventofcode2022.common.InvalidInputException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

public class Day6Part1SolverTest {

    private final Day6Solver day6Solver = new Day6Solver();

    @Test
    public void shouldReturnProperResultForExampleInputFromTaskDescription() {
        String input = "mjqjpqmgbljsphdztnvjfqwrcgsmlb";

        int result = day6Solver.solvePartOne(input);

        assertThat(result).isEqualTo(7);
    }

    @Test
    public void shouldThrowExceptionWhenInputIsEmpty() {
        String input = "";

        assertThatExceptionOfType(InvalidInputException.class)
                .isThrownBy(() -> day6Solver.solvePartOne(input))
                .withMessageContaining("Input is empty.");
    }

    @Test
    public void shouldThrowExceptionWhenCharactersInStringAreNotLetters() {
        String input = "aa$1a";

        assertThatExceptionOfType(InvalidInputException.class)
                .isThrownBy(() -> day6Solver.solvePartOne(input))
                .withMessageContaining("Invalid characters in input. Proper characters: a-z, A-Z.");
    }

    @Test
    public void shouldThrowExceptionWhenMarkerWithUniqueCharactersNotFound() {
        String input = "aabb";

        assertThatExceptionOfType(InvalidInputException.class)
                .isThrownBy(() -> day6Solver.solvePartOne(input))
                .withMessageContaining("Marker with unique characters not found.");
    }
}
