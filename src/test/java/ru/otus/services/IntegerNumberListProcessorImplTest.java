package ru.otus.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.api.services.IntegerNumberListProcessor;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class IntegerNumberListProcessorImplTest {
    private final List<List<Integer>> PARSER_INTEGER_NUMBERS = new ArrayList<>() {{
        add(Arrays.asList(1, 2, 3));
        add(Arrays.asList(0, 1, 2));
        add(Arrays.asList(1, 0, 2));
        add(Arrays.asList(1, 2, 0));
    }};

    private final List<Integer> INTEGER_NUMBERS = Arrays.asList(12, 10, 01);
    private IntegerNumberListProcessor integerNumberListProcessor;

    @Test
    @DisplayName("Getting an integer number from a parsed integer number")
    void getIntegerNumberFromParts() {
        final List<Long> EXPECTED_INTEGER_NUMBERS = Arrays.asList(123L, 12L, 102L, 120L);
        for (int i = 0; i < PARSER_INTEGER_NUMBERS.size(); i++) {
            this.integerNumberListProcessor = new IntegerNumberListProcessorImpl();
            long result = this.integerNumberListProcessor.getIntegerNumberFromParts(this.PARSER_INTEGER_NUMBERS.get(i));
            assertEquals(EXPECTED_INTEGER_NUMBERS.get(i), result);
        }
    }

    @Test
    @DisplayName("Getting parsed integer number without leading zeros")
    void removeLeadingZeros() {
        final List<List<Integer>> EXPECTED_PARSED_INTEGER_NUMBERS = new ArrayList<>() {{
            add(Arrays.asList(1, 2, 3));
            add(Arrays.asList(1, 2));
            add(Arrays.asList(1, 0, 2));
            add(Arrays.asList(1, 2, 0));
        }};

        final List<Integer> EXPECTED_INTEGER_NUMBERS = Arrays.asList(123, 12, 102, 120);
        for (int i = 0; i < INTEGER_NUMBERS.size(); i++) {
            this.integerNumberListProcessor = new IntegerNumberListProcessorImpl();
            List<Integer> resultParsedIntegerNumber = this.integerNumberListProcessor.removeLeadingZeros(this.PARSER_INTEGER_NUMBERS.get(i));
            assertIterableEquals(EXPECTED_PARSED_INTEGER_NUMBERS.get(i), resultParsedIntegerNumber);
        }
    }
}