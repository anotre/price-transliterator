package ru.otus.domain;

import ru.otus.api.domain.IntegerNumber;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.lang.Math;

public class IntegerNumberImpl implements IntegerNumber {
    private final boolean isNegative;
    private final List<Integer> parsedIntegerNumber;
    private final int NUM_IN_CLASS = 3;
    private final int FIRST_INDEX = 0;
    
    public IntegerNumberImpl(List<Integer> parsedIntegerNumber, boolean isNegative) {
        this.parsedIntegerNumber = parsedIntegerNumber;
        this.isNegative = isNegative;
    }

    @Override
    public long getIntegerNumberAbs() {
        long integerNumber = 0;
        int power = this.parsedIntegerNumber.size();

        for (int term : this.parsedIntegerNumber) {
            integerNumber += term * Math.pow(10, power - 1);
            power--;
        }

        return (isNegative) ? -integerNumber : integerNumber;
    }

    @Override
    public List<Integer> getIntegerNumberAbsAsList() {
        return this.parsedIntegerNumber;
    }

    @Override
    public List<List<Integer>> getIntegerNumberAbsAsTriadList() {

        List<List<Integer>> triadList = new ArrayList<>();
        int currentEnd = this.parsedIntegerNumber.size();
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

    @Override
    public long getIntegerNumberAbsBeforeRank(int rank) {
        long integerNumber = Math.abs(this.getIntegerNumberAbs());
        return integerNumber % ((long) Math.pow(10, Math.min(this.parsedIntegerNumber.size(), rank)));
    }

    private int getCurrentStartIndex(int currentEndIndex) {
        return Math.max(currentEndIndex - NUM_IN_CLASS, FIRST_INDEX);
    }

    @Override
    public boolean isNegative() {
        return isNegative;
    }
}
