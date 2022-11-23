package ru.otus.services;

import ru.otus.api.services.CommandHandler;
import ru.otus.api.services.IOService;

import java.util.concurrent.atomic.AtomicBoolean;

public class ApplicationRunner {
    private final IOService ioService;
    private final CommandHandler commandHandler;

    public ApplicationRunner(IOService ioService, CommandHandler commandHandler) {
        this.ioService = ioService;
        this.commandHandler = commandHandler;
    }

    public void run() {
        AtomicBoolean executionFlag = new AtomicBoolean(true);

        while (executionFlag.get()) {
            String command = this.showPromptAndReadCommand();

            if (commandHandler.handleExitCommand(command, ioService, executionFlag)) {
                continue;
            }

            commandHandler.handlePriceInputCommand(command);
        }
    }

    private String showPromptAndReadCommand() {
        final String WELCOME_MESSAGE = "Введите цену (целое число) или \"exit\" для завершения программы:";
        this.ioService.outputString(WELCOME_MESSAGE);

        return ioService.readString();
    }
}
