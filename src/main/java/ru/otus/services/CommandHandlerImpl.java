package ru.otus.services;

import ru.otus.api.domain.IntegerNumber;
import ru.otus.api.services.*;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

public class CommandHandlerImpl implements CommandHandler {
    private final IOService ioService;
    private final IntegerNumberParser integerNumberParser;
    private final PriceTransliterator priceTransliterator;
    private final Logger logger;

    public CommandHandlerImpl(
            IOService ioService,
            IntegerNumberParser integerNumberParser,
            PriceTransliterator priceTransliterator,
            Logger logger) {
        this.ioService = ioService;
        this.integerNumberParser = integerNumberParser;
        this.priceTransliterator = priceTransliterator;
        this.logger = logger;
    }

    @Override
    public boolean handleExitCommand(String command, IOService ioService, AtomicBoolean executionFlag) {
        final String EXIT_COMMAND = "exit";

        if (!command.equalsIgnoreCase(EXIT_COMMAND)) {
            return false;
        }

         executionFlag.getAndSet(false);
        return true;
    }

    @Override
    public void handlePriceInputCommand(String priceString) {
        IntegerNumber integerNumber;
        try {
            integerNumber = this.integerNumberParser.parseIntegerNumber(priceString);
        } catch (IOException exception) {
            this.handleInputError(exception);
            return;
        }
        String outputString = this.priceTransliterator.transliteratePrice(integerNumber);
        ioService.outputString(outputString);
    }

    @Override
    public void handleInputError(IOException exception) {
        final String INPUT_ERROR_MESSAGE = "Incorrect input. Please, try again.";
        this.logger.logExceptionMessage(exception);
        this.ioService.outputString(INPUT_ERROR_MESSAGE);

    }
}
