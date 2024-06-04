package settings;

import jakarta.el.PropertyNotFoundException;

public class SettingStorage {

    public static String getStringProperty(String propertyName, String key) {
        String value = PropertiesFileManager.getPropertyInstance(propertyName).load(key);
        if (value == null) throw new PropertyNotFoundException("Can`t load `" + key + "` from properties file");
        return value;
    }

}
