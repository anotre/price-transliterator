package ru.otus.api.services;

import ru.otus.api.domain.IntegerNumber;

import java.io.IOException;

public interface IntegerNumberParser {
    IntegerNumber parseIntegerNumber(String integerNumber) throws IOException;
}
