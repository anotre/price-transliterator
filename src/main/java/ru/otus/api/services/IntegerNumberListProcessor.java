package ru.otus.api.services;

import java.util.List;

public interface IntegerNumberListProcessor {
    long getIntegerNumberFromParts(List<Integer> parts);
    List<Integer> removeLeadingZeros(List<Integer> parsedIntegerNumber);

}
