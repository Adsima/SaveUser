package config;

import exception.PropertiesException;

import java.io.InputStream;
import java.util.Properties;

public class Config {
    public static final String DB_URL = "db.url";
    public static final String DB_LOGIN = "db.username";
    public static final String DB_PASSWORD = "db.password";

    private static Properties properties = new Properties();

    public static String getProperty(String name) {
        if(properties.isEmpty()) {
            try(InputStream inputStream = Config.class.getClassLoader()
                    .getResourceAsStream("application.properties")) {

                properties.load(inputStream);

            } catch (Exception ex) {
                throw new PropertiesException(ex);
            }
        }
        return properties.getProperty(name);
    }
}
