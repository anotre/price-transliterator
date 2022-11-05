package ru.otus;

import ru.otus.api.services.*;
import ru.otus.services.*;
import ru.otus.api.domain.Currency;

import ru.otus.domain.CurrencyRubleImpl;

public class Main {
    public static void main(String[] args) {
        IOService ioService = new ConsoleIOService();
        IntegerNumberParser integerNumberParser = new IntegerNumberParserImpl();
        IntegerNumberTransliterator integerNumberTransliterator = new IntegerNumberTransliteratorRuImpl();
        Currency currency = new CurrencyRubleImpl();
        CurrencyFormatterRuImpl currencyFormatter = new CurrencyFormatterRuImpl(currency);
        PriceTransliterator priceTransliterator = new PriceTransliteratorImpl(integerNumberTransliterator, currencyFormatter);
        Logger logger = new LoggerStub();
        CommandHandler commandHandler = new CommandHandlerImpl(ioService, integerNumberParser, priceTransliterator, logger);
        ApplicationRunner applicationRunner = new ApplicationRunner(ioService, commandHandler);

        applicationRunner.run();
    }
}