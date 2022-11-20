package ru.otus.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.api.domain.IntegerNumber;
import ru.otus.mocks.domain.IntegerNumberStub;
import ru.otus.mocks.services.CurrencyFormatterStub;
import ru.otus.mocks.services.IntegerNumberTransliteratorStub;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PriceTransliteratorImplTest {
    private PriceTransliteratorImpl priceTransliterator;
    private PriceTransliteratorImpl priceTransliteratorNegative;
    private IntegerNumber integerNumber;
    private IntegerNumber negativeIntegerNumber;

    @Test
    @DisplayName("Getting positive transliterated price")
    void transliteratePrice() {
        this.priceTransliterator = new PriceTransliteratorImpl(new IntegerNumberTransliteratorStub("одна тысяча один"), new CurrencyFormatterStub());
        this.integerNumber = new IntegerNumberStub(1001L, Arrays.asList(1, 0, 0, 1), false);
        String expectedResult = "одна тысяча один рублей";
        assertEquals(expectedResult, this.priceTransliterator.transliteratePrice(this.integerNumber));
    }

    @Test
    @DisplayName("Getting negative transliterated price")
    void transliteratePriceNegative() {
        this.priceTransliteratorNegative = new PriceTransliteratorImpl(new IntegerNumberTransliteratorStub("минус одна тысяча один"), new CurrencyFormatterStub());
        this.negativeIntegerNumber = new IntegerNumberStub(1001L, Arrays.asList(1, 0, 0, 1), true);
        String expectedResult = "минус одна тысяча один рублей";
        assertEquals(expectedResult, this.priceTransliteratorNegative.transliteratePrice(this.negativeIntegerNumber));
    }
}