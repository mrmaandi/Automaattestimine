package request;

import helpers.Constants;

public interface RequestModel {
    public void fetchJsonCurrentWeatherString();
    public void fetchJsonForecastWeatherString();
    public String getCity();
    public Constants.COUNTRY_CODE getCountryCode();
    public Constants.UNIT getUnit();
    public String getJsonData();
}
