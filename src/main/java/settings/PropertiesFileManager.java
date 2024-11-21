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
        try (InputStream input = Files.newInputStream(Paths.get("src/main/resources/static/" + propertyName + ".properties"))) {
            prop = new Properties();
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
