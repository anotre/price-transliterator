package ru.otus.services;

import ru.otus.api.domain.IntegerNumber;
import ru.otus.api.services.CurrencyFormatter;
import ru.otus.api.services.IntegerNumberTransliterator;
import ru.otus.api.services.PriceTransliterator;

public class PriceTransliteratorImpl implements PriceTransliterator {
    private final String SPACE = " ";
    IntegerNumberTransliterator transliterator;
    CurrencyFormatter currencyFormatter;

    public PriceTransliteratorImpl(IntegerNumberTransliterator transliterator, CurrencyFormatter currencyFormatter) {
        this.transliterator = transliterator;
        this.currencyFormatter = currencyFormatter;
    }

    @Override
    public String transliteratePrice(IntegerNumber integerNumber) {
        return this.transliterator.transliterate(integerNumber) + SPACE + this.currencyFormatter.format(integerNumber);
    }
}
