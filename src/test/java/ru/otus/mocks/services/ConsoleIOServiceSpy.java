package ru.otus.mocks.services;

import ru.otus.api.services.IOService;
import java.util.List;

public class ConsoleIOServiceSpy implements IOService {
    private List<String> spyBox;
    public ConsoleIOServiceSpy(List<String> spyBox) {
        this.spyBox = spyBox;
    }
    @Override
    public void outputString(String str) {
        this.spyBox.add(str);
    }

    @Override
    public String readString() {
        return null;
    }
}
