package ru.otus.services;

import ru.otus.api.services.IntegerNumberListProcessor;

import java.util.ArrayList;
import java.util.List;

public class IntegerNumberListProcessorImpl implements IntegerNumberListProcessor {
    private static final int EXPONENTIAL_FUNCTION_BASE = 10;

    @Override
    public long getIntegerNumberFromParts(List<Integer> parts) {
        int power = 0;
        long number = 0;
        int indexLastItem = parts.size() - 1;
        for (int i = indexLastItem; i >= 0; i--) {
            number += parts.get(i) * Math.pow(EXPONENTIAL_FUNCTION_BASE, power++);
        }
        return number;
    }

    @Override
    public List<Integer> removeLeadingZeros(List<Integer> parsedIntegerNumber) {
        for (int i = 0; i < parsedIntegerNumber.size(); i++) {
            if (parsedIntegerNumber.get(i) != 0) {
                return parsedIntegerNumber.subList(i, parsedIntegerNumber.size());
            }
        }
        return new ArrayList<Integer>();
    }
}
