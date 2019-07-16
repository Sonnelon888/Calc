package utils;

import calc.enums.Sign;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static java.lang.String.format;
import static utils.Init.getProperty;

/**
 * Утилитарный класс с методами, используемыми для получения пользовательского ввода
 */
public class InputUserData {
    private boolean repeat;
    private Sign operationValue;
    private static boolean nextIteration;
    private static Double firstUserValue;
    private static Double secondUserValue;
    private static final String functionRequest =
            getProperty("function.value.request");
    private static final String firstRequest =
            getProperty("first.value.request");
    private static final String secondRequest =
            getProperty("second.value.request");
    private static final String operationRequest
            = getProperty("method.value.request");
    private static final String argumentExceprion =
            getProperty("argument.exceprion");
    private static final String operationException =
            getProperty("operation.exceprion");
    private static final String arrayRequest =
            getProperty("array.value.request");
    private static final String arraylengthRequest =
            getProperty("array.length.request");
    private static final String arrayException =
            getProperty("array.exception");
    private BufferedReader reader =
            new BufferedReader(new InputStreamReader(System.in));

    public static String getArgumentExceprion() {
        return argumentExceprion;
    }

    public static boolean isNextIteration() {
        return nextIteration;
    }

    private static void setNextIteration(boolean nextIteration) {
        InputUserData.nextIteration = nextIteration;
    }

    public static Double getFirstUserValue() {
        return firstUserValue;
    }

    private static void setFirstUserValue(Double firstUserValue) {
        InputUserData.firstUserValue = firstUserValue;
    }

    public static Double getSecondUserValue() {
        return secondUserValue;
    }

    private static void setSecondUserValue(Double secondUserValue) {
        InputUserData.secondUserValue = secondUserValue;
    }

    public Sign getOperationValue() {
        return operationValue;
    }

    private void setOperationValue(Sign operationValue) {
        this.operationValue = operationValue;
    }

    /**
     * Метод для получения первого аргумента, используемого в калькуляторе
     */
    public void readfirstValue() {
        setFirstUserValue(readDoubleValue(firstRequest));
    }

    /**
     * Метод для получения второго аргумента, используемого в калькуляторе
     */
    public void readSecondValue() {
        repeat = true;
        while (repeat) {
            try {
                setSecondUserValue(readDoubleValue(secondRequest));
                if (getOperationValue() == Sign.DIV                     //Проверка ввода второго аргумета
                        && getSecondUserValue() == 0) {                 //!= 0 при выполнении деления
                    throw new IllegalArgumentException();
                }
                repeat = false;
            } catch (IllegalArgumentException e) {
                System.out.println(argumentExceprion);
            }
        }
    }

    /**
     * Метод для получения номера выполняемой операции, используемой в калькуляторе
     */
    public void readOperatiobValue() {
        repeat = true;
        while (repeat) {
            try {
                Integer value = readDoubleValue(operationRequest).intValue();
                setOperationValue(Arrays.stream(Sign.values())
                        .filter
                                (val -> val.getNumber().equals(value)) // Проверка на наличие введённой операции
                        .findFirst()
                        .orElseThrow());
                repeat = false;
            } catch (NoSuchElementException e) {
                System.out.println(operationException);
            }
        }
    }
    /**
     * Метод для получения пользовательского ввода
     */
    private String takeUserData() throws IOException {
        return reader.readLine().replaceAll(",", ".");
    }

    /**
     * Метод для получения от пользователя информации
     * о необходимости выполнения повторной операции
     */
    public static void repeat() {
        boolean repeat = true;
        String userValue = null;
        InputUserData in = new InputUserData();
        String question = getProperty("repeat.value.request");
        List<String> positiveSamples = Arrays.asList(                   //Настраиваемый список примером, которые
                getProperty("positive.samples").split(","));         //будут восприняты как положительный ответ
        List<String> negativeSamples = Arrays.asList(                   //Настраиваемый список примером, которые
                getProperty("negative.samples").split(","));         //будут восприняты как негативный ответ
        while (repeat) {
            try {
                System.out.println(question);
                try {
                    userValue = in.takeUserData().toLowerCase();
                } catch (IOException | Error e) {
                    e.getCause();
                }
                if (positiveSamples.contains(userValue)) {
                    repeat = false;
                    setNextIteration(true);
                }
                if (negativeSamples.contains(userValue)) {
                    repeat = false;
                    setNextIteration(false);
                } else {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException e) {
                System.out.println(operationException);
            }
        }
    }

    /**
     * Метод для получения от пользователя массива чисел
     */
    public String[] getArrayFromUser() {
        repeat = true;
        int arrayLength = readDoubleValue(arraylengthRequest).intValue(); //получение информации о колличестве чисел
        String[] line = new String[arrayLength];
        while (repeat) {
            try {
                System.out.println(arrayRequest);
                line = takeUserData().split(" ");
                if (line.length != arrayLength) {
                    throw new IllegalArgumentException();
                }
                repeat = false;
            } catch (IOException | IllegalArgumentException e) {
                System.out.println(format(arrayException, arrayLength));
            }
        }
        return line;
    }

    /**
     * Метод для получения от пользователя числового значения в формате double
     */
    private Double readDoubleValue(String message) {
        repeat = true;
        double result = 0.0;
        while (repeat) {
            try {
                System.out.println(message);
                result = Double.parseDouble(takeUserData());
                repeat = false;
            } catch (Exception | Error e) {
                System.out.println(argumentExceprion);
            }
        }
        repeat = true;
        return result;
    }

    /**
     * Метод для получения номера запускаемой подпрограммы
     */
    public static Integer takeOperationCase() {
        Integer[] checkedValues = {1, 2, 3, 4};
        Integer value = 0;
        boolean repeat = true;
        while (repeat) {
            value = new InputUserData()
                    .readDoubleValue(functionRequest)
                    .intValue();
            try {
                if (!Arrays.asList(checkedValues).contains(value)) {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException e) {
                System.out.println(argumentExceprion);
            }
            repeat = false;
        }
        return value;
    }

    /**
     * Метод для получения от пользователя пути к файлу,
     * который необходимо обработать
     */
    public String readFilePath() {
        repeat = true;
        String result = "";
        while (repeat) {
            try {
                System.out.println("Введите путь к файлу:");
                result = takeUserData();
                repeat = false;
            } catch (Exception | Error e) {
                System.out.println(argumentExceprion);
            }
        }
        repeat = true;
        return result;
    }
}
