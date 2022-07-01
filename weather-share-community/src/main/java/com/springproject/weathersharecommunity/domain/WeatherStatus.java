package com.springproject.weathersharecommunity.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum WeatherStatus {
    HOT("Hot"), WARM("Warm"), BEST("Best"), COLD("Cold"), COOL("Cool");
    private String viewName;

    @JsonCreator
    public static WeatherStatus fromValue(String value) {
        switch (value) {
            case "Hot":
                return WeatherStatus.HOT;
            case "Warm":
                return WeatherStatus.WARM;
            case "Best":
                return WeatherStatus.BEST;
            case "Cold":
                return WeatherStatus.COLD;
            case "Cool":
                return WeatherStatus.COOL;
        }
        return null;
    }
    WeatherStatus(String viewName) {
        this.viewName = viewName;
    }
}
