package utils;

import calc.enums.Sign;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import static java.lang.String.format;
import static utils.Init.getProperty;


public class InputUserData {
    private boolean repeat;
    private Sign operationValue;
    private static boolean nextIteration;
    private static Double firstUserValue;
    private static Double secondUserValue;
    private final Integer[] numOfOperate = {1, 2, 3, 4};
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

    public static boolean isNextIteration() {
        return nextIteration;
    }

    public static void setNextIteration(boolean nextIteration) {
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

    public void readfirstValue() {
        setFirstUserValue(readDoubleValue(firstRequest));
    }

    public void readSecondValue() {
        repeat = true;
        while (repeat) {
            try {
                setSecondUserValue(readDoubleValue(secondRequest));
                if (getOperationValue() == Sign.DIV
                        && getSecondUserValue() == 0) {
                    throw new IllegalArgumentException();
                }
                repeat = false;
            } catch (IllegalArgumentException e) {
                System.out.println(argumentExceprion);
            }
        }
    }

    public void readOperatiobValue() {
        repeat = true;
        while (repeat) {
            try {
                Integer value = readDoubleValue(operationRequest).intValue();
                if (!Arrays.asList(numOfOperate).contains(value)) {
                    throw new IllegalArgumentException();
                }
                setOperationValue(Arrays.stream(Sign.values())
                        .filter
                                (val -> val.getNumber().equals(value))
                        .findFirst()
                        .orElseThrow());
                repeat = false;
            } catch (IllegalArgumentException e) {
                System.out.println(operationException);
            }
        }
    }

    private String takeUserData() throws IOException {
        return reader.readLine().replaceAll(",", ".");
    }

    public static void repeat() {
        boolean repeat = true;
        String userValue = null;
        InputUserData in = new InputUserData();
        String question = getProperty("repeat.value.request");
        List<String> positiveSamples = Arrays.asList(
                getProperty("positive.samples").split(","));
        List<String> negativeSamples = Arrays.asList(
                getProperty("negative.samples").split(","));
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

    public String[] getArrayFromUser() {
        repeat = true;
        int arrayLength = readDoubleValue(arraylengthRequest).intValue();
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

    public static Integer takeOperationCase() {
        Integer[] checkedValues = {1, 2, 3};
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
}
