package com.alazarska.adventofcode2022.day7;

public class MyFile {

    private final Integer size;

    public MyFile(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "MyFile{" +
                "size=" + size +
                '}';
    }
}
