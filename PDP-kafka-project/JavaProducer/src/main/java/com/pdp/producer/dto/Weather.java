package com.pdp.producer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Weather {
    private String UUID;
    private Double temperature;
    private Double humidity;
    private Double pressure;
    private Double windSpeed;
    private Double windDirection;

    public Weather(String UUID, Double temperature, Double humidity, Double pressure, Double windSpeed, Double windDirection) {
        this.UUID = UUID;
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
    }

    public Weather() {
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Double getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(Double windDirection) {
        this.windDirection = windDirection;
    }

    @Override
    public String toString() {
        return "{ \"UUID\" : \"" + UUID + "\", \"temperature\" : " + temperature + ", \"humidity\" : "+ humidity + ", \"pressure\" : "
                + pressure + ", \"windSpeed\" : "+ windSpeed + ", \"windDirection\" : "+ windDirection + "}";
    }
}
