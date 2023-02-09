package com.alazarska.adventofcode2022.day5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.IntStream;

public class CratesStack {
    private final Stack<Character> cratesCharacters;

    public CratesStack(Character... cratesCharacters) {
        this.cratesCharacters = new Stack<>();
        Arrays.stream(cratesCharacters)
                .forEach(this.cratesCharacters::push);
    }

    public List<Character> getCrates() {
        return cratesCharacters;
    }

    public int size() {
        return this.cratesCharacters.size();
    }

    public List<Character> takeCrates(int numberOfCrates) {
        List<Character> listOfCrates = new ArrayList<>();

        IntStream.range(0, numberOfCrates).forEach((i) -> {
            Character firstCrate = this.cratesCharacters.pop();
            listOfCrates.add(firstCrate);
        });
        return listOfCrates;
    }

    public void pushCrates(List<Character> characterList) {
        characterList.forEach(this.cratesCharacters::push);
    }
}
