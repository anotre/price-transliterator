package ru.otus;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ru.otus.api.domain.Currency;
import ru.otus.api.services.*;
import ru.otus.domain.CurrencyRubleImpl;
import ru.otus.services.*;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class MainTest {

    @Test
    @DisplayName("General output test")
    void main() {
        StringBuilder string = new StringBuilder();
        List<String> inputData = Arrays.asList("1001", "-1001", "exit");
        for (String element : inputData) {
            string.append(element);
            string.append("\n");
        }
        final List<String> EXPECTED_RESULTS = Arrays.asList("одна тысяча один рубль", "минус одна тысяча один рубль");

        Stream input = Arrays.stream(inputData.toArray());
        InputStream inputStream = new ByteArrayInputStream(string.toString().getBytes());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream outputStream = new PrintStream(byteArrayOutputStream);

        System.setIn(inputStream);
        System.setOut(outputStream);

        IOService ioService = new ConsoleIOService();
        Logger logger = new LoggerStub();
        IntegerNumberParser integerNumberParser = new IntegerNumberParserImpl();
        IntegerNumberListProcessor integerNumberListProcessor = new IntegerNumberListProcessorImpl();
        IntegerNumberTransliterator integerNumberTransliterator = new IntegerNumberTransliteratorRuImpl(integerNumberListProcessor);
        Currency currency = new CurrencyRubleImpl();
        CurrencyFormatterRuImpl currencyFormatter = new CurrencyFormatterRuImpl(currency);
        PriceTransliterator priceTransliterator = new PriceTransliteratorImpl(integerNumberTransliterator, currencyFormatter);
        PriceTransliterator priceTransliteratorProxy = new PriceTransliteratorProxy(priceTransliterator);
        CommandHandler commandHandler = new CommandHandlerImpl(ioService, integerNumberParser, priceTransliteratorProxy, logger);
        ApplicationRunner applicationRunner = new ApplicationRunner(ioService, commandHandler);

        applicationRunner.run();

        String output = byteArrayOutputStream.toString();
        String[] testResults = output.split("\n");

        for (int i = 0; i < EXPECTED_RESULTS.size(); i++) {
            assertEquals(EXPECTED_RESULTS.get(i), testResults[i * 2 + 1]);
        }
    }
}