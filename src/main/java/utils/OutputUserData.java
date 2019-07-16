package utils;

import static WordCounter.WordCounter.runWordCounter;
import static arrayMax.ArrayMaxFinder.runMaxArrayValue;
import static calc.Calc.runCalc;
import static randomsInArray.ArrayRandoms.runRandomArrays;
import static utils.Init.getProperty;

/**
 * В данном классе реализованы методы с выводм информации
 * в консоль по каждой из подпрограмм
 */
public class OutputUserData {

    public static void printCalcResult() {
        System.out.printf(getProperty("calc.result"), runCalc());
    }

    public static void printArrayMaxValue() {
        System.out.println("Результат: ".concat(runMaxArrayValue()));
    }

    public static void printArrayRandomsValue() {
        System.out.println("Результат: ".concat(runRandomArrays()));
    }

    public static void printWordCounterResult() {
        runWordCounter();
    }
}
