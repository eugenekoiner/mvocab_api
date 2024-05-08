package settings;

import jakarta.el.PropertyNotFoundException;

public class SettingStorage {

    private static final PropertiesFileManager storage = PropertiesFileManager.getPropertyInstance("opensubtitles");

    public static String getStringProperty(String key) {
        String value = storage.load(key);
        if (value == null) throw new PropertyNotFoundException("Can`t load `" + key + "` from properties file");
        return value;
    }

}
