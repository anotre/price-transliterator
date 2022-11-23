package ru.otus.services;

import org.junit.jupiter.api.*;
import ru.otus.api.domain.IntegerNumber;
import ru.otus.api.services.IntegerNumberParser;
import ru.otus.domain.IntegerNumberImpl;
import ru.otus.mocks.domain.IntegerNumberStub;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class IntegerNumberParserImplTest {
    private IntegerNumberParser integerNumberParser;
    private IntegerNumber expectedIntegerNumber;
    private IntegerNumber expectedNegativeIntegerNumber;

    public IntegerNumberParserImplTest() {
        this.expectedIntegerNumber = new IntegerNumberImpl(Arrays.asList(1, 2, 3, 4, 5), false);
        this.expectedNegativeIntegerNumber = new IntegerNumberImpl(Arrays.asList(1, 2, 3, 4, 5), true);
    }

    @BeforeEach
    void init() {
        this.integerNumberParser = new IntegerNumberParserImpl();
    }

    @Test
    @DisplayName("Getting a positive IntegerNumber")
    void parseIntegerNumberMainTest() {
        try {
            IntegerNumber result = this.integerNumberParser.parseIntegerNumber("12345");
            assertEquals(this.expectedIntegerNumber, result);
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    @DisplayName("Wrong line entered")
    void parseIntegerNumberLetterTest() {
        IOException thrown = assertThrows(IOException.class, () -> {
            IntegerNumber result = this.integerNumberParser.parseIntegerNumber("123k5");
        });
    }

    @Test
    @DisplayName("Getting a negative IntegerNumber")
    void parseIntegerNumberNegativeTest() {
        try {
            IntegerNumber result = this.integerNumberParser.parseIntegerNumber("-12345");
            assertEquals(this.expectedNegativeIntegerNumber, result);
        } catch (Exception e) {
            assertTrue(false);
        }

    }

    @Test
    @DisplayName("Negative sign entered incorrectly")
    void parseIntegerNumberWrongNegativeTest() {
        IOException thrown = assertThrows(IOException.class, () -> {
            IntegerNumber result = this.integerNumberParser.parseIntegerNumber("- 12345");
        });
    }

    @Test
    @DisplayName("Empty string entered")
    void parseIntegerNumberEmptyTest() {
        try {
            assertThrows(IOException.class, () -> {
                this.integerNumberParser.parseIntegerNumber("");
            });
        } catch (Exception e) {
            assertTrue(false);
        }
    }
}