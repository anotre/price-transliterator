package ru.otus.api.services;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

public interface CommandHandler {
    boolean handleExitCommand(String command, IOService ioService, AtomicBoolean executionFlag);

    void handlePriceInputCommand(String priceString);

    void handleInputError(IOException exception);
}
