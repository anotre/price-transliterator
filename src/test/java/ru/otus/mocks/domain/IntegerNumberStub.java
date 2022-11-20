package ru.otus.mocks.domain;

import ru.otus.api.domain.IntegerNumber;
import ru.otus.domain.IntegerNumberImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class IntegerNumberStub implements IntegerNumber {
    private long integerNumber;
    private final boolean isNegative;
    private List<Integer> parsedIntegerNumber;

    public IntegerNumberStub(long integerNumber, List<Integer> parsedIntegerNumber, boolean isNegative) {
        this.integerNumber = integerNumber;
        this.parsedIntegerNumber = parsedIntegerNumber;
        this.isNegative = isNegative;
    }
    @Override
    public long getIntegerNumber() {
        return this.integerNumber;
    }

    @Override
    public List<Integer> getIntegerNumberAbsAsList() {
        return this.parsedIntegerNumber;
    }

    @Override
    public List<List<Integer>> getIntegerNumberAbsAsTriadList() {
        List<List<Integer>> triadList = new ArrayList<>();
        final int TRIAD_SIZE = 3;
        int fromIndex = 0;
        int toIndex = this.parsedIntegerNumber.size() % TRIAD_SIZE;
        for (int i = 0; i < this.parsedIntegerNumber.size();) {
            List<Integer> integerList = this.parsedIntegerNumber.subList(fromIndex, toIndex);
            triadList.add(integerList);
            fromIndex = toIndex;
            i = toIndex;
            toIndex += 3;

        }
        return triadList;
    }

    @Override
    public long getIntegerNumberAbsBeforeRank(int rank) {

        return this.integerNumber % (long) Math.pow(10, rank);
    }

    @Override
    public boolean isNegative() {
        return this.isNegative;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IntegerNumberStub that)) return false;
        return this.isNegative == that.isNegative && this.getIntegerNumber() == that.getIntegerNumber();
    }

    @Override
    public int hashCode() {
        return Objects.hash(isNegative, parsedIntegerNumber);
    }
}
