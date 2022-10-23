package ru.otus.api.domain;

import java.util.List;

public interface IntegerNumber {
    long getIntegerNumber();
    
    List<Integer> getIntegerNumberAsList();

    List<List<Integer>> getIntegerNumberAsTriadList();
}
