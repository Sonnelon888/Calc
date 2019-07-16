package calc.operations;


import calc.interfaces.Operation;
/**
 * Класс реализации функционала по сложению аргументов
 */
public class Addition implements Operation {

    public static Double execute() {
        return firstValue + secondValue;
    }
}
