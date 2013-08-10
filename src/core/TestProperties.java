package core;

import java.util.Properties;

/**
 * @author Jesper Öqvist <jesper.oqvist@cs.lth.se>
 */
@SuppressWarnings("serial")
public class TestProperties extends Properties {
	@Override
	public String getProperty(String key, String defaultValue) {
		String value = System.getProperty(key, "");
		if (value.isEmpty()) {
			value = super.getProperty(key, defaultValue);
		}
		return value;
	}
}
