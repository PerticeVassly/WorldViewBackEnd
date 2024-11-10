package org.interaction.interactionbackend.util;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class EnvLoader {
    public static void loadEnv(String path) throws IOException {
        Properties properties = new Properties();
        try (FileReader reader = new FileReader(path)) {
            properties.load(reader);
        }
        properties.forEach((key, value) -> {
            String keyStr = (String) key;
            String valueStr = (String) value;
            System.setProperty(keyStr, valueStr);
        });
    }
}
