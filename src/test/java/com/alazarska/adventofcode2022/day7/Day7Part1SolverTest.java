package com.alazarska.adventofcode2022.day7;

import com.alazarska.adventofcode2022.common.InvalidInputException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

public class Day7Part1SolverTest {

    private final Day7Solver day7Solver = new Day7Solver();

    @Test
    public void shouldReturnProperResultForExampleInputFromTaskDescription() {
        String input = """
                $ cd /
                $ ls
                dir a
                14848514 b.txt
                8504156 c.dat
                dir d
                $ cd a
                $ ls
                dir e
                29116 f
                2557 g
                62596 h.lst
                $ cd e
                $ ls
                584 i
                $ cd ..
                $ cd ..
                $ cd d
                $ ls
                4060174 j
                8033020 d.log
                5626152 d.ext
                7214296 k
                """;

        int result = day7Solver.solvePartOne(input, 100000);

        assertThat(result).isEqualTo(95437);
    }

    @Test
    public void shouldThrowExceptionWhenInputIsEmpty() {
        String input = "";

        assertThatExceptionOfType(InvalidInputException.class)
                .isThrownBy(() -> day7Solver.solvePartOne(input, 0))
                .withMessageContaining("Input is empty.");
    }

    @Test
    public void shouldThrowExceptionWhenInputInFirstLineContainUnknownCommand() {
        String input = """
                aaa
                """;

        assertThatExceptionOfType(InvalidInputException.class)
                .isThrownBy(() -> day7Solver.solvePartOne(input, 0))
                .withMessageContaining("Unknown command: 'aaa'. In first line you should switch to the root directory by command '$ cd /'.");
    }

    @Test
    public void shouldThrowExceptionWhenInputContainUnknownCommand() {
        String input = """
                $ cd /
                $ ls
                aaa
                """;

        assertThatExceptionOfType(InvalidInputException.class)
                .isThrownBy(() -> day7Solver.solvePartOne(input, 0))
                .withMessageContaining("Unknown command: 'aaa'.");
    }

    @Test
    public void shouldThrowExceptionWhenCommandHasInvalidLength() {
        String input = """
                $ cd /
                $ cd a a
                """;

        assertThatExceptionOfType(InvalidInputException.class)
                .isThrownBy(() -> day7Solver.solvePartOne(input, 0))
                .withMessageContaining("Invalid command: '$ cd a a'.");
    }

    @Test
    public void shouldThrowExceptionWhenCommandHasBadNumberFormat() {
        String input = """
                $ cd /
                1a a
                """;

        assertThatExceptionOfType(InvalidInputException.class)
                .isThrownBy(() -> day7Solver.solvePartOne(input, 0))
                .withMessageContaining("Invalid command: '1a a'.");
    }
}
