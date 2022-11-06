package ru.otus.services;

import ru.otus.api.services.IntegerNumberListProcessor;
import ru.otus.api.services.IntegerNumberTransliterator;
import ru.otus.api.domain.IntegerNumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class IntegerNumberTransliteratorRuImpl implements IntegerNumberTransliterator {
    private static final String SPACE = " ";
    private static final int LANGUAGE_SPECIFIC_FROM_INCLUSIVE = 10;
    private static final int LANGUAGE_SPECIFIC_TO_INCLUSIVE = 19;

    private static final int TENS_RANK_INDEX = 2; // сделать enum где будут все три индекса
    private static final int UNITS_RANK_INDEX = 1;
    private static final int HUNDREDS_RANK_INDEX = 3;
    private static final int UNITS_CLASS_INDEX = 1;

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
        put(0, "");
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

    IntegerNumberListProcessor integerNumberListProcessor;

    public IntegerNumberTransliteratorRuImpl(IntegerNumberListProcessor integerNumberListProcessor) {
        this.integerNumberListProcessor = integerNumberListProcessor;
    }

    public String transliterate(IntegerNumber integerNumber) {

        List<List<Integer>> triadList = integerNumber.getIntegerNumberAbsAsTriadList();
        StringBuilder transliteratedIntegerNumber = new StringBuilder("");
        List<String> transliteratedIntegerNumberList = new ArrayList<>();


        if (integerNumber.isNegative()) {
            transliteratedIntegerNumberList.add(this.transliterateNegativeSign());
        }

        for (int i = 0; i < triadList.size(); i++) {
            List<Integer> currentTriad = triadList.get(i);
            transliteratedIntegerNumberList.add(this.transliterateTriad(currentTriad, triadList.size() - i, triadList.size()));
        }

        transliteratedIntegerNumberList.removeAll(Arrays.asList(""));
        return this.combineStrings(transliteratedIntegerNumberList);
    }

    private String transliterateTriad(List<Integer> triad, int integerNumberClassNumber, int numberOfClasses) {
        List<String> transliteratedStringList = new ArrayList<>();
        List<Integer> triadLocal = this.integerNumberListProcessor.removeLeadingZeros(triad.subList(0, triad.size()));
        int triadLocalSize = triadLocal.size();

        if (numberOfClasses == UNITS_RANK_INDEX && triadLocalSize == 0) {
            triadLocal.add(0);
            triadLocalSize = triadLocal.size();
        } else if (numberOfClasses != UNITS_RANK_INDEX && triadLocalSize == 0) {
            return "";
        }

        if (triadLocalSize == HUNDREDS_RANK_INDEX) {
            transliteratedStringList.add(this.transliterateHundreds(triadLocal.get(0)));
        }

        int tens = (int) ((triadLocalSize > TENS_RANK_INDEX) ?
                this.integerNumberListProcessor.getIntegerNumberFromParts(triadLocal.subList(1, triadLocalSize)) :
                this.integerNumberListProcessor.getIntegerNumberFromParts(triadLocal));

        if (tens >= LANGUAGE_SPECIFIC_FROM_INCLUSIVE && tens <= LANGUAGE_SPECIFIC_TO_INCLUSIVE) {
            transliteratedStringList.add(specificTransliterationMap.get(tens));
        } else if (tens < LANGUAGE_SPECIFIC_FROM_INCLUSIVE) {
            transliteratedStringList.add(this.transliterateUnits(triadLocal.get(triadLocalSize - 1), integerNumberClassNumber, numberOfClasses, triadLocalSize));
        } else {
            transliteratedStringList.add(this.transliterateTens(triadLocal.get(triadLocalSize - 2)));
            transliteratedStringList.add(this.transliterateUnits(triadLocal.get(triadLocalSize - 1), integerNumberClassNumber, numberOfClasses, triadLocalSize));
        }

        transliteratedStringList.add(this.transliterateIntegerNumberClassByTriad(triadLocal, integerNumberClassNumber));

        transliteratedStringList.removeAll(Arrays.asList(""));

        return this.combineStrings(transliteratedStringList);
    }

    private String transliterateUnits(int unitsRankNumber, int integerNumberClassIndex, int numberOfClasses, int triadSize) {
        if (triadSize == UNITS_RANK_INDEX && numberOfClasses != UNITS_CLASS_INDEX && unitsRankNumber == 0) {
            return this.transliterationUnitsMap.get(unitsRankNumber);
        }

        if (unitsRankNumber == 0) {
            return "";
        }

        if (integerNumberClassIndex != TENS_RANK_INDEX) {
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

    private String transliterateIntegerNumberClassByTriad(List<Integer> integerNumberTriad, int classIndex) {
        String transliteratedClass;
        final int HUNDREDS_RANK = 100;
        final int TENS_RANK = 10;

        if (classIndex == 0) {
            return "";
        }

        long integerNumberLocal = this.integerNumberListProcessor.getIntegerNumberFromParts(integerNumberTriad) % HUNDREDS_RANK;

        if (integerNumberLocal > LANGUAGE_SPECIFIC_FROM_INCLUSIVE && integerNumberLocal <= LANGUAGE_SPECIFIC_TO_INCLUSIVE) {
            transliteratedClass = integerNumberClassNames.get(classIndex)[2];
        } else {
            int lastDigit = (int) integerNumberLocal % TENS_RANK;
            if (lastDigit == 1) {
                transliteratedClass = integerNumberClassNames.get(classIndex)[0];
            } else if (lastDigit >= 2 && lastDigit <= 4) {
                transliteratedClass = integerNumberClassNames.get(classIndex)[1];
            } else {
                transliteratedClass = integerNumberClassNames.get(classIndex)[2];
            }
        }

        return transliteratedClass.toString();
    }

    private String transliterateNegativeSign() {
        return "минус";
    }

    private String combineStrings(List<String> stringList) {
        StringBuilder outputString = new StringBuilder();
        for (int i = 0; i < stringList.size(); i++) {
            String stringBuffer = stringList.get(i);

            if (!stringBuffer.equalsIgnoreCase("")) {
                outputString.append(stringBuffer);
            }

            if (i < stringList.size() - 1) {
                outputString.append(SPACE);
            }
        }
        return outputString.toString();
    }
}
