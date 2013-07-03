package com.tigerby.utils;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.util.Collections;
import java.util.Map;

/**
 * <p> title here </p>
 *
 * @author <a href="mailto:bongyeonkim@gmail.com">Kim Bryan</a>
 * @version 1.0
 */
public class PropertiesManager {
    private static Map<String, Configuration> configurations = Collections.emptyMap();

    static {
        try {
            configurations.put("default", new PropertiesConfiguration("application.properties"));
        } catch (ConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public static Configuration getConfiguration(String name) {
        return configurations.get(name);
    }

    public static void setConfiguration(String name, Configuration configuration) {
        configurations.put(name, configuration);
    }

    public static Object getProperty(String configurationName, String PropertyName) {
        return configurations.get(configurationName).getProperty(PropertyName);
    }

    public static String getStringProperty(String configurationName, String PropertyName) {
        return configurations.get(configurationName).getString(PropertyName);
    }

}
