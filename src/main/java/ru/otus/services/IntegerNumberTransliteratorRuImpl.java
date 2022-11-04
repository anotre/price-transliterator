package ru.otus.services;

import ru.otus.api.services.IntegerNumberTransliterator;
import ru.otus.api.domain.IntegerNumber;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.lang.Math;

public class IntegerNumberTransliteratorRuImpl implements IntegerNumberTransliterator {
    private static final int EXPONENTIAL_FUNCTION_BASE = 10;
    private static final String SPACE = " ";
    private static final int LANGUAGE_SPECIFIC_FROM_INCLUSIVE = 10;
    private static final int LANGUAGE_SPECIFIC_TO_INCLUSIVE = 20;

    private final Map<Integer, String> transliterationUnitsMap = new HashMap<>() {{
        put(0, "ноль");
        put(1, "один");
        put(2, "два");
        put(3, "три");
        put(4, "четыре");
        put(5, "пять");
        put(6, "шесть");
        put(7, "семь");
        put(8, "восемь");
        put(9, "девять");
    }};

    private final Map<Integer, String> transliterationSpecificUnitsMap = new HashMap<>() {{
        put(0, "");
        put(1, "одна");
        put(2, "две");
        put(3, "три");
        put(4, "четыре");
        put(5, "пять");
        put(6, "шесть");
        put(7, "семь");
        put(8, "восемь");
        put(9, "девять");
    }};
    private final Map<Integer, String> transliterationTensMap = new HashMap<>() {{
        put(1, "десять");
        put(2, "двадцать");
        put(3, "тридцать");
        put(4, "сорок");
        put(5, "пятьдесят");
        put(6, "шестьдесят");
        put(7, "семьдесят");
        put(8, "восемьдесят");
        put(9, "девяносто");
    }};
    private final Map<Integer, String> transliterationHundredsMap = new HashMap<>() {{
        put(1, "сто");
        put(2, "двести");
        put(3, "триста");
        put(4, "четыреста");
        put(5, "пятьсот");
        put(6, "шестьсот");
        put(7, "семьсот");
        put(8, "восемьсот");
        put(9, "девятьсот");
    }};
    private final Map<Integer, String> specificTransliterationMap = new HashMap<>() {{
        put(10, "десять");
        put(11, "одиннадцать");
        put(12, "двенадцать");
        put(13, "тринадцать");
        put(14, "четырнадцать");
        put(15, "пятнадцать");
        put(16, "шестнадцать");
        put(17, "семнадцать");
        put(18, "восемнадцать");
        put(19, "девятнадцать");
    }};

    private final Map<Integer, String[]> integerNumberClassNames = new HashMap<>() {{
        put(1, new String[] {"", "", ""});
        put(2, new String[] {"тысяча", "тысячи", "тысяч"});
        put(3, new String[] {"миллион", "миллиона", "миллионов"});
        put(4, new String[] {"миллиард", "миллиарда", "миллиардов"});
        put(5, new String[] {"триллион", "триллиона", "триллионов"});
        put(6, new String[] {"квадриллион", "квадриллиона", "квадриллионов"});
        put(7, new String[] {"квинтиллион", "квинтиллиона", "квинтиллионов"});
        put(8, new String[] {"сикстиллион", "сикстиллиона", "сикстиллионов"});

    }};
    public IntegerNumberTransliteratorRuImpl() {}

    public String transliterate(IntegerNumber integerNumber) {

        List<List<Integer>> triadList = integerNumber.getIntegerNumberAbsAsTriadList();
        StringBuilder transliteratedIntegerNumber = new StringBuilder("");
        Map<Integer, String> transliteratedTriads = new HashMap<>();

        if (integerNumber.isNegative()) {
            transliteratedIntegerNumber.append(this.transliterateNegativeSign());
            transliteratedIntegerNumber.append(SPACE);
        }

        for (int i = 0; i < triadList.size(); i++) {
            List<Integer> currentTriad = triadList.get(i);
            transliteratedTriads.put(i, this.transliterateTriad(currentTriad, triadList.size() - i, triadList.size()));
            transliteratedIntegerNumber.append(transliteratedTriads.get(i));

            if (transliteratedTriads.get(i).equals("") || i == triadList.size() - 1) {
                continue;
            }

            transliteratedIntegerNumber.append(SPACE);
        }
        return transliteratedIntegerNumber.toString();
    }

    private String transliterateTriad(List<Integer> triad, int integerNumberClassNumber, int numberOfClasses) {//
        final int TENS_RANK_INDEX = 2;
        final int UNITS_RANK = 1;

        StringBuilder transliteratedString = new StringBuilder("");

        List<Integer> triadLocal = this.removeLeadingZeros(triad.subList(0, triad.size()));

        if (numberOfClasses == UNITS_RANK && triadLocal.size() == 0) {
            triadLocal.add(0);
        } else if (numberOfClasses != UNITS_RANK && triadLocal.size() == 0) {
            return "";
        }

        final int TRIAD_SIZE = triadLocal.size();

        int tens = (int) ((TRIAD_SIZE > TENS_RANK_INDEX) ? this.getIntegerNumberFromParts(triadLocal.subList(1, TRIAD_SIZE)) : this.getIntegerNumberFromParts(triadLocal));

        if (TRIAD_SIZE == UNITS_RANK) {
            transliteratedString.append(this.transliterateUnits(triadLocal.get(0), integerNumberClassNumber));

            if (integerNumberClassNumber != UNITS_RANK) {
                transliteratedString.append(SPACE);
                transliteratedString.append(this.transliterateIntegerNumberClass(triadLocal, integerNumberClassNumber));
            }

            return transliteratedString.toString();
        }

        if (tens >= LANGUAGE_SPECIFIC_FROM_INCLUSIVE && tens < LANGUAGE_SPECIFIC_TO_INCLUSIVE) {
            transliteratedString.append(specificTransliterationMap.get(tens));
        } else {
            transliteratedString.append(this.transliterateUnits(triadLocal.get(TRIAD_SIZE - 1), integerNumberClassNumber));
            transliteratedString.insert(0, SPACE);
            transliteratedString.insert(0, this.transliterateTens(triadLocal.get(TRIAD_SIZE - 2)));
        }

        if (TRIAD_SIZE == 3) {
            transliteratedString.insert(0, SPACE);
            transliteratedString.insert(0, this.transliterateHundreds(triadLocal.get(0)));
        }

        if (integerNumberClassNumber != UNITS_RANK) {
            transliteratedString.append(SPACE);
            transliteratedString.append(this.transliterateIntegerNumberClass(triadLocal, integerNumberClassNumber));
        }

        return transliteratedString.toString();
    }

    private String transliterateUnits(int unitsRankNumber, int integerNumberClass) {
        if (integerNumberClass != 2) {
            return this.transliterationUnitsMap.get(unitsRankNumber);
        } else {
            return this.transliterationSpecificUnitsMap.get(unitsRankNumber);
        }
    }

    private String transliterateTens(int tensRankNumber) {
        return this.transliterationTensMap.get(tensRankNumber);
    }

    private String transliterateHundreds(int hundredsRankNumber) {
        return this.transliterationHundredsMap.get(hundredsRankNumber);
    }

    private long getIntegerNumberFromParts(List<Integer> parts) {
        int power = 0;
        long number = 0;
        int indexLastItem = parts.size() - 1;
        for (int i = indexLastItem; i >= 0; i--) {
            number += parts.get(i) * Math.pow(EXPONENTIAL_FUNCTION_BASE, power++);
        }
        return number;
    }

    private List<Integer> removeLeadingZeros(List<Integer> parsedIntegerNumber) {
        for (int i = 0; i < parsedIntegerNumber.size(); i++) {
            if (parsedIntegerNumber.get(i) != 0) {
                return parsedIntegerNumber.subList(i, parsedIntegerNumber.size());
            }
        }
        return new ArrayList<Integer>();
    }

    private boolean isZeroTriad(List<Integer> triad) {
        return this.getIntegerNumberFromParts(triad) == 0;
    }

    private String transliterateIntegerNumberClass(List<Integer> integerNumber, int classIndex) {
        StringBuilder transliteratedClass = new StringBuilder("");
        final int HUNDREDS_RANK = 100;
        final int TENS_RANK = 10;

        if (classIndex == 0) {
            return "";
        }

        long integerNumberLocal = this.getIntegerNumberFromParts(integerNumber) % HUNDREDS_RANK;

        if (integerNumberLocal > LANGUAGE_SPECIFIC_FROM_INCLUSIVE && integerNumberLocal < LANGUAGE_SPECIFIC_TO_INCLUSIVE) {
            transliteratedClass.append(integerNumberClassNames.get(classIndex)[2]);

        } else {
            int lastDigit = (int) integerNumberLocal % TENS_RANK;
            if (lastDigit == 1) {
                transliteratedClass.append(integerNumberClassNames.get(classIndex)[0]);
            } else if (lastDigit >= 2 && lastDigit <= 4) {
                transliteratedClass.append(integerNumberClassNames.get(classIndex)[1]);
            } else {
                transliteratedClass.append(integerNumberClassNames.get(classIndex)[2]);
            }
        }

        return transliteratedClass.toString();
    }

    private String transliterateNegativeSign() {
        return "минус";
    }
}
