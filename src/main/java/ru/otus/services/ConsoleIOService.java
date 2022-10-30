package ru.otus.services;

import ru.otus.api.services.IOService;

import java.io.PrintStream;
import java.util.Scanner;

public class ConsoleIOService implements IOService {
    private final PrintStream out;
    private final Scanner in;

    public ConsoleIOService() {
        this.out = System.out;
        this.in = new Scanner(System.in);
    }
    @Override
    public void outputString(String str) {
        System.out.println(str);
    }

    @Override
    public String readString() {
        return this.in.nextLine();
    }
}
