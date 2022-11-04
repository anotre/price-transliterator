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
        boolean isNegative = false;
        StringBuilder strIntegerNumber = new StringBuilder(integerNumber);
        Character firstSign = strIntegerNumber.charAt(0);

        if (firstSign.equals('-')) {
            isNegative = true;
            strIntegerNumber.deleteCharAt(0);
        }

        List<Integer> parsedIntegerNumber = new ArrayList<Integer>();
        if (integerNumber.matches("\\D")) {
            throw new IOException("Wrong input");
        }
        long buffer = Long.parseLong(strIntegerNumber.toString());

        if (buffer == 0 && isNegative) {
            isNegative = false;
        }

        int power = 1;
        do {
            parsedIntegerNumber.add((int) (buffer % NUMBER_SUBCLASS_DETERMINATOR));
            buffer /= NUMBER_SUBCLASS_DETERMINATOR;
            power++;
        } while (buffer != 0);

        Collections.reverse(parsedIntegerNumber);
        
        return new IntegerNumberImpl(parsedIntegerNumber, isNegative);
    }
}
