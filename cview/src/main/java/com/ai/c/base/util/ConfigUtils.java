package com.ai.c.base.util;

import org.apache.commons.configuration.CombinedConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.DefaultConfigurationBuilder;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;

public final class ConfigUtils {

	private static final Logger logger = Logger.getLogger(ConfigUtils.class);

	private static Configuration config = new CombinedConfiguration();

	static {
		try {
			DefaultConfigurationBuilder configurationBuilder = new DefaultConfigurationBuilder(
					"app.config.xml");
			configurationBuilder
					.setReloadingStrategy(new FileChangedReloadingStrategy());
			config = configurationBuilder.getConfiguration();
		} catch (ConfigurationException e) {
			logger.error("Init Configuration Error", e);
		}
	}

	public static boolean containsKey(final String key) {
		return config.containsKey(key);
	}

	public static Iterator<String> getKeys() {
		return config.getKeys();
	}

	public static Object getProperty(String key) {
		return config.getProperty(key);
	}

	public static boolean getBoolean(String key) {
		return config.getBoolean(key);
	}

	public static int getInt(final String key) {
		return config.getInt(key);
	}

	public static int getIntValue(final String key) {
		return config.getInt(key);
	}

	public static long getLong(final String key) {
		return config.getLong(key);
	}

	public static long getLongValue(final String key) {
		return config.getLong(key);
	}

	public static double getDouble(final String key) {
		return config.getDouble(key);
	}

	public static double getDoubleValue(final String key) {
		return config.getDouble(key);
	}

	public static BigDecimal getBigDecimal(String key) {
		return config.getBigDecimal(key);
	}

	public static BigInteger getBigInteger(String key) {
		return config.getBigInteger(key);
	}

	public static String getStringValue(String key) {
		return config.getString(key);
	}

	public static String[] getStringArray(String key) {
		return config.getStringArray(key);
	}

	public static List<Object> getList(String key) {
		return config.getList(key);
	}
}