package ru.otus.mocks.services;

import ru.otus.api.domain.IntegerNumber;
import ru.otus.api.services.CurrencyFormatter;

public class CurrencyFormatterStub implements CurrencyFormatter {
    @Override
    public String format(IntegerNumber integerNumber) {
        return "рублей";
    }
}
