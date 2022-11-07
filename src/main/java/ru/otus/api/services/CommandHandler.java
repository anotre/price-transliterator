package ru.otus.api.services;


import java.util.concurrent.atomic.AtomicBoolean;

public interface CommandHandler {
    boolean handleExitCommand(String command, IOService ioService, AtomicBoolean executionFlag);

    void handlePriceInputCommand(String priceString);
}
