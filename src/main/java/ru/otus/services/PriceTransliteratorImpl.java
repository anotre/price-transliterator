package ru.otus.services;

import ru.otus.api.domain.IntegerNumber;
import ru.otus.api.services.CurrencyFormatter;
import ru.otus.api.services.IntegerNumberTransliterator;
import ru.otus.api.services.PriceTransliterator;

public class PriceTransliteratorImpl implements PriceTransliterator {
    private final String SPACE = " ";
    IntegerNumber integerNumber;
    IntegerNumberTransliterator transliterator;
    CurrencyFormatter currencyFormatter;

    public PriceTransliteratorImpl(IntegerNumber integerNumber, IntegerNumberTransliterator transliterator, CurrencyFormatter currencyFormatter) {
        this.integerNumber = integerNumber;
        this.transliterator = transliterator;
        this.currencyFormatter = currencyFormatter;
    }

    @Override
    public String transliteratePrice() {
        return this.transliterator.transliterate(this.integerNumber) + SPACE + this.currencyFormatter.format();
    }
}
