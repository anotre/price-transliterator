package ru.otus.mocks.services;

import ru.otus.api.domain.IntegerNumber;
import ru.otus.api.services.IntegerNumberTransliterator;

public class IntegerNumberTransliteratorStub implements IntegerNumberTransliterator {
     private String transliteratedIntegerNumber;

     public IntegerNumberTransliteratorStub(String transliteratedIntegerNumber) {
         this.transliteratedIntegerNumber = transliteratedIntegerNumber;
     }
    @Override
    public String transliterate(IntegerNumber integerNumber) {
        return this.transliteratedIntegerNumber;
    }
}
