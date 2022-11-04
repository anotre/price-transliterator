package ru.otus.api.domain;

import java.util.List;

public interface IntegerNumber {
    long getIntegerNumberAbs();
    
    List<Integer> getIntegerNumberAbsAsList();

    List<List<Integer>> getIntegerNumberAbsAsTriadList();

    long getIntegerNumberAbsBeforeRank(int rank);

    boolean isNegative();
}
