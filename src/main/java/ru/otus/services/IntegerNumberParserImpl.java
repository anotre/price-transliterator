package ru.otus.services;

import ru.otus.api.services.IntegerNumberParser;
import ru.otus.api.domain.IntegerNumber;
import ru.otus.domain.IntegerNumberImpl;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class IntegerNumberParserImpl implements IntegerNumberParser {
    private final static int NUMBER_SUBCLASS_DETERMINATOR = 10;

    public IntegerNumber parseIntegerNumber(String integerNumber) throws IOException {
        StringBuilder strIntegerNumber = new StringBuilder(integerNumber);

        if (strIntegerNumber.isEmpty()) {
            throw new IOException("Wrong input");
        }

        boolean isNegative = this.isNegative(strIntegerNumber.toString());

        if (isNegative) {
            strIntegerNumber.deleteCharAt(0);
        }

        if (strIntegerNumber.toString().matches("^.*\\D.*")) {
            throw new IOException("Wrong input");
        }

        long buffer = Long.parseLong(strIntegerNumber.toString());

        if (buffer == 0 && isNegative) {
            isNegative = false;
        }

        List<Integer> parsedIntegerNumber = new ArrayList<>();
        int power = 1;
        do {
            parsedIntegerNumber.add((int) (buffer % NUMBER_SUBCLASS_DETERMINATOR));
            buffer /= NUMBER_SUBCLASS_DETERMINATOR;
            power++;
        } while (buffer != 0);

        Collections.reverse(parsedIntegerNumber);
        
        return new IntegerNumberImpl(parsedIntegerNumber, isNegative);
    }

    private boolean isNegative(String strIntegerNumber) {
        Character firstSign = strIntegerNumber.charAt(0);

        return firstSign.equals('-');
    }
}
