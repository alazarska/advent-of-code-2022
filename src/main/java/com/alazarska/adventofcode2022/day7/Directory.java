package com.alazarska.adventofcode2022.day7;

import java.util.ArrayList;
import java.util.List;

public class Directory {

    private final String name;
    private final Directory parent;
    private final List<MyFile> filesList = new ArrayList<>();
    private final List<Directory> directoryList = new ArrayList<>();

    public Directory(String name, Directory parent) {
        this.name = name;
        this.parent = parent;
    }

    public Directory getParent() {
        return parent;
    }

    public void addDirectory(Directory directory) {
        directoryList.add(directory);
    }

    public void addFile(MyFile file) {
        filesList.add(file);
    }

    public List<Directory> getDirectoryList() {
        return directoryList;
    }

    public int getSize() {
        int filesSize = filesList.stream()
                .mapToInt(MyFile::getSize)
                .sum();
        int directoriesSize = directoryList.stream()
                .mapToInt(Directory::getSize)
                .sum();
        return filesSize + directoriesSize;
    }

    @Override
    public String toString() {
        return name;
    }
}
