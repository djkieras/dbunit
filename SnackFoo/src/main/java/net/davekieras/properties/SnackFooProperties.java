package net.davekieras.properties;

public interface SnackFooProperties {
	
	public static final String TIME_ZONE_ID = "CDT";

	public String getSnackListUrl();
	
	public void setSnackListUrl(String snackListUrl);
	
	public String getSnackSuggestionUrl();
	
	public void setSnackSuggestionUrl(String snackSuggestionUrl);
	
}
