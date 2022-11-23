package ru.otus.api.services;

import ru.otus.api.domain.IntegerNumber;

public interface  Transliterator {
    String transliterate(IntegerNumber integerNumber);
}
