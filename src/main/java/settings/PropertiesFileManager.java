package settings;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertiesFileManager {
    private static volatile PropertiesFileManager propertyInstance;
    private static Properties prop;

    public static PropertiesFileManager getPropertyInstance(String propertyName) {
        if (PropertiesFileManager.propertyInstance == null) {
            synchronized (PropertiesFileManager.class) {
                PropertiesFileManager.propertyInstance = new PropertiesFileManager(propertyName);
            }
        }
        return PropertiesFileManager.propertyInstance;
    }

    public String load(String key) {
        return prop.getProperty(key);
    }

    private PropertiesFileManager(String propertyName) {
        try (InputStream input = Files.newInputStream(Paths.get("src/main/resources/static/" + propertyName + ".properies"))) {
            prop = new Properties();
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
