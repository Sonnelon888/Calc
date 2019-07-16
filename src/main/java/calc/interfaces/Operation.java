package calc.interfaces;

import static utils.InputUserData.getFirstUserValue;
import static utils.InputUserData.getSecondUserValue;

/**
 * Интерфейс который инкапсулирует общие для операций используемые данные
 */
public interface Operation {
    Double firstValue = getFirstUserValue();
    Double secondValue = getSecondUserValue();

}
