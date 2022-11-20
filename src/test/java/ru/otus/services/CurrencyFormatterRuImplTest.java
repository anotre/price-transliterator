package ru.otus.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.api.domain.Currency;
import ru.otus.api.domain.IntegerNumber;
import ru.otus.api.services.CurrencyFormatter;
import ru.otus.domain.CurrencyRubleImpl;
import ru.otus.mocks.domain.IntegerNumberStub;
import ru.otus.mocks.services.CurrencyFormatterStub;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyFormatterRuImplTest {

    private final List<Long> EXPECTED_INTEGER_NUMBERS = new ArrayList<>() {{
        add(111L);
        add(101L);
        add(102L);
        add(105L);
    }};


    private final List<List<Integer>> EXPECTED_PARSED_INTEGER_NUMBERS = new ArrayList<>() {{
        add(Arrays.asList(1, 1, 1));
        add(Arrays.asList(1, 0, 1));
        add(Arrays.asList(1, 1, 2));
        add(Arrays.asList(1, 0, 5));
    }};

    private final List<String> EXPECTED_RESULTS = new ArrayList<>() {{
        add("рублей");
        add("рубль");
        add("рубля");
        add("рублей");
    }};

    private Currency currency;
    private IntegerNumber integerNumber;
    private CurrencyFormatter currencyFormatter;

    @BeforeEach
    void init() {
        this.currency = new CurrencyRubleImpl();
        this.currencyFormatter = new CurrencyFormatterRuImpl(this.currency);
        //this.integerNumber = new IntegerNumberStub(EXPECTED_INTEGER_NUMBERS, EXPECTED_PARSED_INTEGER_NUMBERS, false);
    }

    @Test
    @DisplayName("Getting the currency in the correct format")
    void format() {
        for (int i = 0; i < EXPECTED_INTEGER_NUMBERS.size(); i++) {
            this.integerNumber = new IntegerNumberStub(EXPECTED_INTEGER_NUMBERS.get(i), EXPECTED_PARSED_INTEGER_NUMBERS.get(i), false);
            assertEquals(EXPECTED_RESULTS.get(i), this.currencyFormatter.format(this.integerNumber));
        }
    }
}