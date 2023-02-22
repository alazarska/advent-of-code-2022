package com.alazarska.adventofcode2022.day7;

import com.alazarska.adventofcode2022.common.InvalidInputException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

public class Day7Part2SolverTest {

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

        int result = day7Solver.solvePartTwo(input, 70000000, 30000000);

        assertThat(result).isEqualTo(24933642);
    }

    @Test
    public void shouldThrowExceptionWhenDirectoryWithNeededFreeSpaceIsNotFound() {
        String input = """
                $ cd /
                $ ls
                dir a
                10 a.txt
                """;

        assertThatExceptionOfType(InvalidInputException.class)
                .isThrownBy(() -> day7Solver.solvePartTwo(input, 10, 20))
                .withMessageContaining("Directory with at least 20 size not found.");
    }
}
