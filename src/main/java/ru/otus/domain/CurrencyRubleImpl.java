package ru.otus.domain;

import java.util.List;
import java.util.ArrayList;
import ru.otus.api.domain.Currency;

public class CurrencyRubleImpl implements Currency {
    private final List<String> currencyName = new ArrayList<>() {{
        add("рубль");
        add("рубля");
        add("рублей");
    }};

    public List<String> getCurrencyName() {
        return this.currencyName;
    }
}
