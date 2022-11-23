package ru.otus.api.services;

import ru.otus.api.domain.IntegerNumber;

public interface PriceTransliterator {
    String transliteratePrice(IntegerNumber integerNumber);
}
