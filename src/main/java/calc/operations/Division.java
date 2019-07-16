package calc.operations;


import calc.interfaces.Operation;
/**
 * Класс реализации функционала по делению аргументов
 */
public class Division implements Operation {

    public static Double execute() {
        Double result;
        if(secondValue == 0){
            throw new IllegalArgumentException("Деление на 0 невозможно");
        }else {
            result = firstValue / secondValue;
        }
        return result;
    }
}
