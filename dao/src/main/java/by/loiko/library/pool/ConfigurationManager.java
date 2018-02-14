package by.loiko.library.pool;

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

    /** The resource bundle. */
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("configuration");

    /**
     * Instantiates a new configuration manager.
     */
    private ConfigurationManager() {
    }

    /**
     * Gets the property  to connect DB
     *
     * @param key the key
     * @return the property
     */
    public static String getProperty(String key) {
        String value;

        try {
            value = resourceBundle.getString(key);
        } catch (MissingResourceException e) {
            logger.log(Level.FATAL, "Resource wasn't found: " + key + " program is stopped.");
            throw new RuntimeException(e);
        }

        return value;
    }
}
