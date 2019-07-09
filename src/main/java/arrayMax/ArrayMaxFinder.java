package arrayMax;

import utils.InputUserData;

import java.util.Arrays;

public class ArrayMaxFinder {
    private static InputUserData data = new InputUserData();

    public static String runMaxArrayValue() {
        return Arrays.stream(
                data.getArrayFromUser())
                .sorted((first, second) -> second.length() - first.length())
                .distinct()
                .findFirst()
                .orElse("");
    }
}
