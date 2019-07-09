package utils;

import static arrayMax.ArrayMaxFinder.runMaxArrayValue;
import static calc.Calc.runCalc;
import static randomsInArray.ArrayRandoms.runRandomArrays;
import static utils.Init.getProperty;

public class OutputUserData {

    public static void printCalcResult() {
        System.out.printf(getProperty("calc.result"), runCalc());
    }

    public static void printArrayMaxValue() {
        System.out.println("Результат: ".concat(runMaxArrayValue()));
    }

    public static void printArrayRandomsValue(){
        System.out.println("Результат: ".concat(runRandomArrays()));
    }
}
