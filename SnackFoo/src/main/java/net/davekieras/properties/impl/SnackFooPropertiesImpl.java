package net.davekieras.properties.impl;

import net.davekieras.properties.SnackFooProperties;

public class SnackFooPropertiesImpl implements SnackFooProperties {

	private String snackListUrl;
	private String snackSuggestionUrl;

	@Override
	public String getSnackListUrl() {
		return snackListUrl;
	}

	@Override
	public void setSnackListUrl(String snackListUrl) {
		this.snackListUrl = snackListUrl;
	}

	@Override
	public String getSnackSuggestionUrl() {
		return snackSuggestionUrl;
	}

	@Override
	public void setSnackSuggestionUrl(String snackSuggestionUrl) {
		this.snackSuggestionUrl = snackSuggestionUrl;
	}

}
