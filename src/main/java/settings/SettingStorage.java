package settings;

import jakarta.el.PropertyNotFoundException;

public class SettingStorage {

    public synchronized static String getStringProperty(String propertyName, String key) {
        String value = PropertiesFileManager.getPropertyInstance(propertyName).load(key);
        if (value == null) throw new PropertyNotFoundException("Can`t load `" + key + "` from properties " + propertyName + " file");
        return value;
    }

    public synchronized static Integer getIntegerProperty(String propertyName, String key) {
        String value = PropertiesFileManager.getPropertyInstance(propertyName).load(key);
        if (value == null) throw new PropertyNotFoundException("Can`t load `" + key + "` from properties " + propertyName + " file");
        return Integer.parseInt(value);
    }

}
