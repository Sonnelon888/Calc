import utils.Init;

import static utils.InputUserData.*;
import static utils.OutputUserData.*;

public class Main {
    public static void main(String[] args) {
        boolean process = true;
        Init.getProperties();
        while (process) {
            while (process) {
                switch (takeOperationCase()) {
                    case 1:
                        printCalcResult();
                        process = false;
                        break;
                    case 2:
                        printArrayMaxValue();
                        process = false;
                        break;
                    case 3:
                        printArrayRandomsValue();
                        process = false;
                        break;
                }
            }
            repeat();
            process = isNextIteration();
        }
    }
}