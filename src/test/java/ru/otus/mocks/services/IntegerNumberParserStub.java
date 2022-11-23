package ru.otus.mocks.services;

import ru.otus.api.domain.IntegerNumber;
import ru.otus.api.services.IntegerNumberParser;

import java.io.IOException;

public class IntegerNumberParserStub  implements IntegerNumberParser {
    private IntegerNumber integerNumber;

    public IntegerNumberParserStub(IntegerNumber integerNumber) {
        this.integerNumber = integerNumber;
    }
    @Override
    public IntegerNumber parseIntegerNumber(String integerNumber) throws IOException {
        return this.integerNumber;
    }
}