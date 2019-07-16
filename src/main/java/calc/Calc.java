package calc;

import utils.InputUserData;
/**
 * В данном классе реализована команда запуска подпрограммы
 * калькулятора
 */
public class Calc {
    private static InputUserData data = new InputUserData();

    public static Double runCalc(){
        data.readfirstValue();
        data.readOperatiobValue();
        data.readSecondValue();
        return data.getOperationValue().result();
    }

}
