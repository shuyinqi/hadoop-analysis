package com.mapbar.analyzelog.core;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Constants of the class static and keeping the final (static and
 * immutable) characteristics, but also has a Properties file configuration of
 * the features available, Applied mainly in the Java language features of
 * static initialization code.<br> 
 * e.g: <pre> 
 * public final class Constants extends ConfigurableConstants{
 *      public static final APPLICATION_NAME = getProperty("application.name");
 * }
 * </pre>
 * 
 * @author <a href="mailto:dengfg@mapbar.com">邓飞鸽</a>
 */
public class ConfigurableConstants {
    protected static Logger logger = LoggerFactory.getLogger(ConfigurableConstants.class);
    protected static Properties p = new Properties();

    /**
     * To the static load the specified property in the Properties.
     * 
     * @throws IOException if file not found or can not readable.
     */
	public static void init(String propertyFileName) throws IOException {
		InputStream in = null;
		try {
			logger.debug("Loading the property file [{}]", propertyFileName);
			in = ClassLoader.getSystemResource(propertyFileName).openStream();
			if (in != null) {
				p.load(in);
			}
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}

	/**
	 * Properties to obtain from the key property value.
	 * 
	 * @param key property key.
	 * @param defaultValue If not fond in properties return that value.
	 */
    public static String getProperty(String key, String defaultValue) {
        return p.getProperty(key, defaultValue);
    }
    
    /**
	 * Properties to obtain from the key property value.
	 * 
	 * @param key property key.
	 */
    public static String getProperty(String key) {
        return p.getProperty(key);
    }

    public static int getIntProperty(String key, int defaultValue) {
    	return NumberUtils.toInt(getProperty(key), defaultValue);
    }

    public static int getIntProperty(String key) {
    	return getIntProperty(key, 0);
    }
}
