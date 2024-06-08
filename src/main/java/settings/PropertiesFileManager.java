package settings;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertiesFileManager {
    private static volatile PropertiesFileManager propertyInstance;
    private static Properties prop;
//todo: сейчас использование невозмможно так как происходит путица всех переменных даже в одном потоке, надо разобраться как это работает
    public static PropertiesFileManager getPropertyInstance(String propertyName) {
        if (propertyInstance == null) {
            synchronized (PropertiesFileManager.class) {
                propertyInstance = new PropertiesFileManager(propertyName);
            }
        }
        return propertyInstance;
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
