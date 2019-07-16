package WordCounter;

import utils.InputUserData;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Map.Entry.comparingByKey;
import static java.util.stream.Collectors.toMap;
import static utils.InputUserData.getArgumentExceprion;

/**
 * В данном классе реализованна подпрограмма
 * подсчёта и сортировки слов в файле
 */
public class WordCounter {

    /**
     * В данном методе реализованно
     * получение слов и их колличество
     * отстортированное по алфавиту
     */
    private static Map<String, Integer> countWordsFromFile(String filePath) throws IOException {
        String value = "";
        List<String> sorted;
        int i;
        FileInputStream stream = new FileInputStream(new File(filePath));// Получаем файл по указанному пути
        do {
            i = stream.read();
            if (i != -1) value += ((char) i);                           //записываем содержание файла в переменную
        } while (i != -1);
        sorted = Arrays.asList(value.split(" "));                   //делим файл на слова по разделителю в виде пробела
        return sorted.stream()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.summingInt(n -> 1)))               //формируем Map<слово, кол-во использований>
                .entrySet()
                .stream()
                .sorted(comparingByKey())                           //представляем Map в виде списка и сортируем по ключу
                .collect(toMap(Map.Entry::getKey,
                        Map.Entry::getValue,                        //возвращаем отсортированные значения в виде Map
                        (e1, e2) -> e2, LinkedHashMap::new));
    }

    /**
     * В данном методе реализован
     * запуск подпрограммы
     */
    public static void runWordCounter() {
        boolean repeat = true;
        InputUserData data = new InputUserData();
        while (repeat) {
            try {
                String fileData = data.readFilePath();
                countWordsFromFile(fileData)              // проходим по Map и выводим все значения в консоль
                        .forEach((k, v) -> System.out.printf("%s втречается %s раз\n", k, v));
                countWordsFromFile(fileData)
                        .entrySet()
                        .stream()                        //поиск и вывод максимально встречающегося раз значения
                        .max(Map.Entry.comparingByValue())
                        .ifPresent(k ->
                                System.out.printf("%s встречается максимальное колличество раз - %s",
                                        k.getKey(), k.getValue()));
                repeat = false;
            } catch (Exception e) {
                System.out.println(getArgumentExceprion());
            }
        }
    }
}
