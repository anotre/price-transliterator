package ru.otus.services;

import ru.otus.api.services.IntegerNumberParser;
import ru.otus.api.domain.IntegerNumber;
import ru.otus.domain.IntegerNumberImpl;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class IntegerNumberParserImpl implements IntegerNumberParser {
    private final static int NUMBER_SUBCLASS_DETERMINATOR = 10;

    public IntegerNumber parseIntegerNumber(long integerNumber) {
        List<Integer> parsedIntegerNumber = new ArrayList<Integer>();
        long buffer = integerNumber;

        int power = 1;
        do {
            parsedIntegerNumber.add((int) (buffer % NUMBER_SUBCLASS_DETERMINATOR));
            buffer /= NUMBER_SUBCLASS_DETERMINATOR;
            power++;
        } while (buffer != 0);

        Collections.reverse(parsedIntegerNumber);
        
        return new IntegerNumberImpl(parsedIntegerNumber);
    }
}
