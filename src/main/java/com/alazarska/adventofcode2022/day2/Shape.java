package com.alazarska.adventofcode2022.day2;

public enum Shape {
    ROCK,
    PAPER,
    SCISSORS;

    public Shape getWinner() {
        return switch (this) {
            case ROCK -> PAPER;
            case PAPER -> SCISSORS;
            case SCISSORS -> ROCK;
        };
    }

    public Shape getLoser() {
        return switch (this) {
            case PAPER -> ROCK;
            case SCISSORS -> PAPER;
            case ROCK -> SCISSORS;
        };
    }
}
