package ru.otus.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ru.otus.api.domain.IntegerNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


class IntegerNumberImplTest {
    IntegerNumber positiveIntegerNumber;
    IntegerNumber negativeIntegerNumber;

    @Test
    @DisplayName("The received value is equal to the expected one")
    void getIntegerNumberEqualsTest() {
        final long FROM = -100_000_000;
        final long TO = 100_000_000;
        for (long i = FROM; i <= TO; i++) {
            boolean isNegative = i < 0;
            IntegerNumber integerNumber = new IntegerNumberImpl(this.getParsedIntegerNumberAbs(i), isNegative);
            assertEquals(i, integerNumber.getIntegerNumber());
        }
    }

    @Test
    @DisplayName("Getting an IntegerNumber as a triadList")
    void getIntegerNumberAbsAsTriadList() {
        final int MAX_RANK_INDEX = 18;
        long value = 10;
        for (long i = 0; i < MAX_RANK_INDEX; i++) {
            List<Integer> parsedIntegerNumberAbs = this.getParsedIntegerNumberAbs((long) Math.pow(value, i));
            IntegerNumber IntegerNumber = new IntegerNumberImpl(parsedIntegerNumberAbs, false);
            for (List<Integer> integerNumberList : IntegerNumber.getIntegerNumberAbsAsTriadList()) {
                assertTrue(integerNumberList.size() <= 3 && integerNumberList.size() > 0);
            }
        }
    }

    @Test
    @DisplayName("Getting an IntegerNumber up to a given digit")
    void getIntegerNumberAbsBeforeRank() {
        for (int i = 1; i <= 11; i++) {
            List<Integer> parsedIntegerNumber = this.getParsedIntegerNumberAbs(12030040005L);
            int PARSED_INTEGER_NUMBER_SIZE = parsedIntegerNumber.size();
            IntegerNumber integerNumber = new IntegerNumberImpl(parsedIntegerNumber, false);
            long expectedNumber = this.getIntegerNumber(parsedIntegerNumber.subList(PARSED_INTEGER_NUMBER_SIZE - i, PARSED_INTEGER_NUMBER_SIZE));

            assertEquals(expectedNumber, integerNumber.getIntegerNumberAbsBeforeRank(i));
        }
    }

    private List<Integer> getParsedIntegerNumberAbs(long integerNumber) {
        final int NUMBER_SUBCLASS_DETERMINATOR = 10;
        List<Integer> parsedIntegerNumber = new ArrayList<>();
        integerNumber = Math.abs(integerNumber);
        int power = 1;
        do {
            parsedIntegerNumber.add((int) (integerNumber % NUMBER_SUBCLASS_DETERMINATOR));
            integerNumber /= NUMBER_SUBCLASS_DETERMINATOR;
            power++;
        } while (integerNumber != 0);

        Collections.reverse(parsedIntegerNumber);
        return parsedIntegerNumber;
    };

    private long getIntegerNumber(List<Integer> parsedIntegerNumber) {
        long integerNumber = 0;
        final int INTEGER_NUMBER_SIZE = parsedIntegerNumber.size();
        for (int i = 1; i <= INTEGER_NUMBER_SIZE; i++) {
            integerNumber += parsedIntegerNumber.get(INTEGER_NUMBER_SIZE - i) * Math.pow(10, i - 1);
        }
        return integerNumber;
    }
}