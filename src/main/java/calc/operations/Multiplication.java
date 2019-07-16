package calc.operations;

import calc.interfaces.Operation;
/**
 * Класс реализации функционала по перемножению аргументов
 */
public class Multiplication implements Operation {

    public static Double execute() {
        return firstValue * secondValue;
    }
}
