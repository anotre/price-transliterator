package ru.otus.domain;

import ru.otus.api.domain.IntegerNumber;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public class IntegerNumberImpl implements IntegerNumber {
    private final List<Integer> parsedIntegerNumber;
    
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

    public List<List<Integer>> getIntegerNumberAsTriadList() { // нужен правильный порядок, сейчас - обратный
        final int NUM_IN_CLASS = 3;
        List<List<Integer>> triadList = new ArrayList<>();
        int end = this.parsedIntegerNumber.size();
        int currentStart = 0;
        int currentEnd = Math.min(end, NUM_IN_CLASS);

        while (true) {
            triadList.add(this.parsedIntegerNumber.subList(currentStart, currentEnd));

            if (currentEnd == end) {
                break;
            }

            currentStart = currentEnd;
            currentEnd = Math.min(currentEnd + NUM_IN_CLASS, end);
        }

        return triadList;
    }
}
