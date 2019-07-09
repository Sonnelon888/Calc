package calc;

import utils.InputUserData;

public class Calc {
    private static InputUserData data = new InputUserData();

    public static Double runCalc(){
        data.readfirstValue();
        data.readOperatiobValue();
        data.readSecondValue();
        return data.getOperationValue().result();
    }

}
