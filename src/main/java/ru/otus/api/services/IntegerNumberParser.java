package ru.otus.api.services;

import ru.otus.api.domain.IntegerNumber;

public interface IntegerNumberParser {
    IntegerNumber parseIntegerNumber(long integerNumber);
}
