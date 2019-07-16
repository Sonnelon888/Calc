package calc.operations;

import calc.interfaces.Operation;
/**
 * Класс реализации функционала по вычитанию аргументов
 */
public class Subtraction implements Operation {

    public static Double execute() {
        return firstValue - secondValue;
    }
}
