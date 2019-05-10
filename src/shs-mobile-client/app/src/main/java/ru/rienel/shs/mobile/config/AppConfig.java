package ru.rienel.shs.mobile.config;

import java.util.Properties;

import android.content.Context;

import ru.rienel.shs.mobile.util.PropertyReader;

public class AppConfig {

	private String headControllerEndpoint;

	public AppConfig(Context context) {
		PropertyReader propertyReader = new PropertyReader(context);
		Properties properties = propertyReader.getProperties("app.properties");
		headControllerEndpoint = properties.getProperty("hc.endpoint");
	}

	public String getHeadControllerEndpoint() {
		return headControllerEndpoint;
	}
}
