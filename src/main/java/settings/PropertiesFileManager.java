package settings;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesFileManager {
    private static final Map<String, PropertiesFileManager> instances = new HashMap<>();
    private Properties prop;

    public static PropertiesFileManager getPropertyInstance(String propertyName) {
        synchronized (PropertiesFileManager.class) {
            return instances.computeIfAbsent(propertyName, PropertiesFileManager::new);
        }
    }

    public String load(String key) {
        return prop.getProperty(key);
    }

    private PropertiesFileManager(String propertyName) {
        prop = new Properties();
        String envPath = System.getenv((propertyName + "_properties").toUpperCase() + "_PATH");
        String filePath = envPath != null
                ? envPath
                : "src/main/resources/static/" + propertyName + ".properties"; // Локальный путь

        try (InputStream input = Files.newInputStream(Paths.get(filePath))) {
            prop.load(input);
        } catch (IOException ex) {
            System.err.println("Failed to load properties file: " + filePath);
            ex.printStackTrace();
        }
    }
    }

