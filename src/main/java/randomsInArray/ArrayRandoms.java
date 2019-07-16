package randomsInArray;

import java.util.Arrays;
import java.util.Comparator;

import static java.lang.String.format;

public class ArrayRandoms {

    // Метод генерирует массив с размерностью 20 и заполняемый рандомными значениями от -10 до 10
    private static Integer[] genArray() {
        Integer[] values = new Integer[20];
        for (int i = 0; i < values.length; i++) {
            values[i] = (int) (Math.random() * (20 + 1) - 10);
        }
        System.out.println("Сгенерирован массив: "
                .concat(Arrays.toString(values)));
        return values;
    }

    /**
     * Метод, который возвращает максимальное негативное значение массива
     */
    private static Integer findMaxNegativeIndex(Integer[] values) {
        return Arrays.stream(values)
                .filter(i -> i < 0)
                .max(Comparator.naturalOrder())
                .orElse(0);
    }

    /**
     * Метод, который возвращает минимальное позитивное значение массива
     */
    private static Integer findMinPositiveIndex(Integer[] values) {
        return Arrays.stream(values).filter(i -> i > 0)
                .sorted()
                .findFirst()
                .orElse(0);
    }

    /**
     * В данном методе реализована команда запуска подпрограммы
     * по выполнению задачи с заменой минимального позитивного
     * и максимального негативного значения в сгенерированном массиве
     */
    public static String runRandomArrays() {
        Integer[] result = genArray();
        int firstReplaced = findMaxNegativeIndex(result);
        int secondReplaced = findMinPositiveIndex(result);
        int firstIndex = Arrays.asList(result).indexOf(firstReplaced);
        int secondIndex = Arrays.asList(result).indexOf(secondReplaced);
        System.out.println(format(
                "Максимальное негативное значение = %s\n" +
                        "Минимальное позитивное значение = %s",
                firstReplaced, secondReplaced));
        result[secondIndex] = firstReplaced;
        result[firstIndex] = secondReplaced;
        return Arrays.toString(result);
    }
}
