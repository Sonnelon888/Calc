package utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Properties;

public class Init {
    private static Properties properties = new Properties();

    public static void getProperties() {
        try (FileReader fr = new FileReader(
                new File("src/main/resources/application.properties"),
                Charset.forName("windows-1251"))) {
            properties.load(fr);
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public static String getProperty(String value) {
        return properties.getProperty(value);
    }
}
