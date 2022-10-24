package ru.otus.domain;

import ru.otus.api.domain.IntegerNumber;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.lang.Math;

public class IntegerNumberImpl implements IntegerNumber {
    private final List<Integer> parsedIntegerNumber;
    private final int NUM_IN_CLASS = 3;
    private final int FIRST_INDEX = 0;
    
    public IntegerNumberImpl(List<Integer> parsedIntegerNumber) {
        this.parsedIntegerNumber = parsedIntegerNumber;
    }

    public long getIntegerNumber() {
        long integerNumber = 0;
        int power = this.parsedIntegerNumber.size();

        for (int term : this.parsedIntegerNumber) {
            integerNumber += term * Math.pow(10, power - 1);
            power--;
        }

        return integerNumber;
    }

    public List<Integer> getIntegerNumberAsList() {
        return this.parsedIntegerNumber;
    }

    public List<List<Integer>> getIntegerNumberAsTriadList() {

        List<List<Integer>> triadList = new ArrayList<>();
        int end = this.parsedIntegerNumber.size();
        int currentEnd = end;
        int currentStart = this.getCurrentStartIndex(currentEnd);

        while (true) {
            triadList.add(this.parsedIntegerNumber.subList(currentStart, currentEnd));

            if (currentStart == FIRST_INDEX) {
                break;
            }

            currentEnd = currentStart;
            currentStart = this.getCurrentStartIndex(currentEnd);
        }

        Collections.reverse(triadList);

        return triadList;
    }

    private int getCurrentStartIndex(int currentEndIndex) {
        return Math.max(currentEndIndex - NUM_IN_CLASS, FIRST_INDEX);
    }
}
