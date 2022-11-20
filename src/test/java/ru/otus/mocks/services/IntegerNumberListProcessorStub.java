package ru.otus.mocks.services;

import ru.otus.api.services.IntegerNumberListProcessor;

import java.util.Arrays;
import java.util.List;

public class IntegerNumberListProcessorStub implements IntegerNumberListProcessor {

    @Override
    public  long getIntegerNumberFromParts(List<Integer> parts) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < parts.size(); i++) {
            result.append(parts.get(i));
        }

        return Long.parseLong(result.toString());
    }

    @Override
    public List<Integer> removeLeadingZeros(List<Integer> parsedIntegerNumber) {
        int index;

        for (index = 0; index < parsedIntegerNumber.size(); index++) {
            if (parsedIntegerNumber.get(index) != 0) {
                break;
            }
        }

        return parsedIntegerNumber.subList(index, parsedIntegerNumber.size());
    }
}
