package ru.otus.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import ru.otus.api.services.*;
import ru.otus.mocks.domain.IntegerNumberStub;
import ru.otus.mocks.services.ConsoleIOServiceSpy;
import ru.otus.mocks.services.CurrencyFormatterStub;
import ru.otus.mocks.services.IntegerNumberParserStub;
import ru.otus.mocks.services.IntegerNumberTransliteratorStub;
import ru.otus.services.CommandHandlerImpl;
import ru.otus.api.domain.IntegerNumber;
import ru.otus.services.LoggerStub;
import ru.otus.services.PriceTransliteratorImpl;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;


class CommandHandlerImplTest {

    private CommandHandler commandHandler;
    private IOService ioService;
    private List<String> spyBox;
    private final long EXPECTED_INTEGER_NUMBER = 101L;
    private final List<Integer> EXPECTED_PARSED_INTEGER_NUMBER = new ArrayList<>(){{
        add(1);
        add(0);
        add(1);
    }};

    private final String EXPECTED_TRANSLITERATED_INTEGER_NUMBER = "сто один";

    @BeforeEach
    void init() {
        this.spyBox = new ArrayList<>();
        this.ioService = new ConsoleIOServiceSpy(this.spyBox);
        IntegerNumber integerNumber = new IntegerNumberStub(this.EXPECTED_INTEGER_NUMBER, this.EXPECTED_PARSED_INTEGER_NUMBER, false);
        IntegerNumberParser integerNumberParser = new IntegerNumberParserStub(integerNumber);
        IntegerNumberTransliterator integerNumberTransliterator = new IntegerNumberTransliteratorStub(this.EXPECTED_TRANSLITERATED_INTEGER_NUMBER);
        CurrencyFormatter currencyFormatter = new CurrencyFormatterStub();
        PriceTransliterator priceTransliterator = new PriceTransliteratorImpl(integerNumberTransliterator, currencyFormatter);
        Logger logger = new LoggerStub();

        this.commandHandler = new CommandHandlerImpl(this.ioService, integerNumberParser, priceTransliterator, logger);
    }

    @Test
    @DisplayName("Checking return values for different values")
    void handleExitCommandReturnValues() {
        AtomicBoolean executionFlag = new AtomicBoolean(true);
        boolean result = this.commandHandler.handleExitCommand("exit", this.ioService, executionFlag);
        assertTrue(result);

        executionFlag.getAndSet(true);
        result = this.commandHandler.handleExitCommand("not_exit", this.ioService, executionFlag);
        assertFalse(result);
    }

    @Test
    @DisplayName("Checking executionFlag values for different values")
    void handleExitCommandExecutionFlag() {
        AtomicBoolean executionFlag = new AtomicBoolean(true);
        this.commandHandler.handleExitCommand("exit", this.ioService, executionFlag);
        assertFalse(executionFlag.get());

        executionFlag.getAndSet(true);
        this.commandHandler.handleExitCommand("not_exit", this.ioService, executionFlag);
        assertTrue(executionFlag.get());
    }

    @Test
    @DisplayName("Checks the return value against the expected value")
    void handlePriceInputCommand() {
        this.commandHandler.handlePriceInputCommand(String.valueOf(this.EXPECTED_INTEGER_NUMBER));
        assertEquals("сто один рублей", this.spyBox.get(0));
    }
}