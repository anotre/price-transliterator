package ru.otus.services;

import ru.otus.api.domain.IntegerNumber;
import ru.otus.api.services.PriceTransliterator;

import java.util.HashMap;

public class PriceTransliteratorProxy implements PriceTransliterator {

    private final PriceTransliterator priceTransliterator;

    private HashMap<IntegerNumber, String> previousRequests;

    public PriceTransliteratorProxy(PriceTransliterator priceTransliterator) {
        this.priceTransliterator = priceTransliterator;
        this.previousRequests = new HashMap<>();
    }
    @Override
    public String transliteratePrice(IntegerNumber integerNumber) {
        if (previousRequests.containsKey(integerNumber)) {
            return previousRequests.get(integerNumber);
        } else {
            String transliteratePrice = priceTransliterator.transliteratePrice(integerNumber);
            previousRequests.put(integerNumber, transliteratePrice);

            return transliteratePrice;
        }
    }
}
