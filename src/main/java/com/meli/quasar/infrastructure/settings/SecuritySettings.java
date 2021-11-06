package com.meli.quasar.infrastructure.settings;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "security")
public class SecuritySettings {
	private String accessToken;

	public String getAccessToken() {
		return accessToken;
	}


	

}
