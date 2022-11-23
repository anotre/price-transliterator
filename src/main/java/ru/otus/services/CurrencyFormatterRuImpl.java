package ru.otus.services;

import ru.otus.api.domain.Currency;
import ru.otus.api.domain.IntegerNumber;
import ru.otus.api.services.CurrencyFormatter;

public class CurrencyFormatterRuImpl implements CurrencyFormatter {

    Currency currency;
    //IntegerNumber integerNumber;
    public CurrencyFormatterRuImpl(Currency currency) {
        this.currency = currency;
    }

    @Override
    public String format(IntegerNumber integerNumber) {
        final int EXPONENTIAL_FUNCTION_BASE = 10;
        final int LANGUAGE_SPECIFIC_FROM_INCLUSIVE = 10;
        final int LANGUAGE_SPECIFIC_TO_EXCLUSIVE = 20;

        String currencyForm;
        long integerNumberPart = integerNumber.getIntegerNumberAbsBeforeRank(2);
        long firstRank = integerNumberPart % EXPONENTIAL_FUNCTION_BASE;

        if ((integerNumberPart >= LANGUAGE_SPECIFIC_FROM_INCLUSIVE) && (integerNumberPart < LANGUAGE_SPECIFIC_TO_EXCLUSIVE)) {
            currencyForm = this.currency.getCurrencyNames().get(2);
        } else if (firstRank == 1) {
            currencyForm = this.currency.getCurrencyNames().get(0);
        } else if (firstRank >= 2 && firstRank <= 4) {
            currencyForm = this.currency.getCurrencyNames().get(1);
        } else {
            currencyForm = this.currency.getCurrencyNames().get(2);
        }

        return currencyForm;
    }
}
