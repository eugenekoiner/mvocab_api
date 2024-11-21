package mvocab_api.security;

import static settings.SettingStorage.getIntegerProperty;
import static settings.SettingStorage.getStringProperty;

public class SecurityConstant {
    public static final long JWT_EXPIRATION = getIntegerProperty("security", "jwt.accessToken.exp");
    public static final String JWT_SECRET = getStringProperty("security", "jwt.secret");
}
