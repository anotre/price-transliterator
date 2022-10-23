package ru.otus.domain;

import ru.otus.api.domain.IntegerNumber;
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
}
