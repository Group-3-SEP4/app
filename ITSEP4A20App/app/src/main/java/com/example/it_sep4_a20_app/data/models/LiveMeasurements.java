package com.example.it_sep4_a20_app.data.models;

public class LiveMeasurements {
    private int measurementId;
    private int humidityPercentage;
    private int carbonDioxide;
    private double temperature;
    private int servoPositionPercentage;
    private String deviceEui;

    public LiveMeasurements(int humidityPercentage, int carbonDioxide, double temperature) {
        this.humidityPercentage = humidityPercentage;
        this.carbonDioxide = carbonDioxide;
        this.temperature = temperature;
    }

    public int getHumidityPercentage() {
        return humidityPercentage;
    }

    public void setHumidityPercentage(int humidityPercentage) {
        this.humidityPercentage = humidityPercentage;
    }

    public int getCarbonDioxide() {
        return carbonDioxide;
    }

    public void setCarbonDioxide(int carbonDioxide) {
        this.carbonDioxide = carbonDioxide;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getDeviceId() {
        return deviceEui;
    }

    public void setDeviceId(String deviceId) {
        this.deviceEui = deviceId;
    }

    public int getServoPositionPercentage() {
        return servoPositionPercentage;
    }

    public String getDeviceEui() {
        return deviceEui;
    }
}
