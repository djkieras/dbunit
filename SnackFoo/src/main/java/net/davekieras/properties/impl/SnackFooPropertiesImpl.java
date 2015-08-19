package net.davekieras.properties.impl;

import org.springframework.stereotype.Component;

import net.davekieras.properties.SnackFooProperties;

@Component
public class SnackFooPropertiesImpl implements SnackFooProperties {

	private String snackListUrl;
	private String snackSuggestionUrl;
	
	public String getSnackListUrl() {
		return snackListUrl;
	}
	public void setSnackListUrl(String snackListUrl) {
		this.snackListUrl = snackListUrl;
	}
	public String getSnackSuggestionUrl() {
		return snackSuggestionUrl;
	}

	public void setSnackSuggestionUrl(String snackSuggestionUrl) {
		this.snackSuggestionUrl = snackSuggestionUrl;
	}
}
