package manager;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/***
 Author: Aliaksei Loika
 Date: 14.12.2017
 ***/
public class ConfigurationManager {
    private static Logger logger = LogManager.getLogger();

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("configuration");

    private ConfigurationManager() {

    }

    public static String getProperty(String key) {
        String value;

        try {
            value = resourceBundle.getString(key);
        } catch (MissingResourceException e) {
            logger.log(Level.FATAL, "Resource was found: " + key + " program is stopped.");
            throw new RuntimeException(e);
        }

        return value;
    }
}
