package ru.otus.api.services;

import ru.otus.api.domain.Price;

public interface PriceParser {
    Price parsePrice(Long price);
}
