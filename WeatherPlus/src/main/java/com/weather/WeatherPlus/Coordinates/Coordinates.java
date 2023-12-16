package com.weather.WeatherPlus.Coordinates;

public class Coordinates {
    private Double lat;
    private Double lng;
    private String cityName;

    public Coordinates(Double lat, Double lng, String cityName) {
        this.lat = lat;
        this.lng = lng;
        this.cityName = cityName;
    }


    public Double getLat() {
        return lat;
    }

    public Double getLng() {
        return lng;
    }

    public String getCityName() {
        return cityName;
    }


    public void setLat(Double lat) {
        this.lat = lat;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
