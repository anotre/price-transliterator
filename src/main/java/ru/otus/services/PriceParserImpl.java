package ru.otus.services;

import ru.otus.api.services.PriceParser;
import ru.otus.services.NumberClasses;
import ru.otus.api.domain.Price;
import ru.otus.domain.PriceImpl;

import java.util.HashMap;

public class PriceParserImpl implements PriceParser {
    public Price parsePrice(Long price) {
        HashMap<NumberClasses,Integer> parsedPrice = new HashMap<NumberClasses,Integer>();
        Long buffer = price;

        for (NumberClasses numberClassItem : NumberClasses.values()) {
            parsedPrice.put(numberClassItem ,(int) (buffer % 1000));
            buffer /= 1000;

            if (buffer == 0) {
                break;
            }
        }

        return new PriceImpl(parsedPrice);
    }
}
