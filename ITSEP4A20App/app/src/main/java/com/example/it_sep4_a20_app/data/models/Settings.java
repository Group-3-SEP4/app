package com.example.it_sep4_a20_app.data.models;

import java.util.List;

public class Settings {
    private int settingsId;
    private String lastUpdate;
    private double temperatureSetpoint;
    private int ppmMin;
    private int ppmMax;
    private List<Device> device;

    public Settings() {
    }

    public Settings(int settingsId, String lastUpdate,
                    double temperatureSetPoint, int co2Min, int co2Max, List<Device> devices) {
        this.settingsId = settingsId;
        this.lastUpdate = lastUpdate;
        this.temperatureSetpoint = temperatureSetPoint;
        this.ppmMin = co2Min;
        this.ppmMax = co2Max;
        this.device = devices;
    }

    public int getSettingsId() {
        return settingsId;
    }

    public void setSettingsId(int settingsId) {
        this.settingsId = settingsId;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public double getTemperatureSetpoint() {
        return temperatureSetpoint;
    }

    public void setTemperatureSetpoint(double temperatureSetpoint) {
        this.temperatureSetpoint = temperatureSetpoint;
    }

    public void setPpmMin(int ppmMin) {
        this.ppmMin = ppmMin;
    }

    public void setPpmMax(int ppmMax) {
        this.ppmMax = ppmMax;
    }

    public int getPpmMin() {
        return ppmMin;
    }

    public int getPpmMax() {
        return ppmMax;
    }

    public List<Device> getRooms() {
        return device;
    }

    public void setRooms(List<Device> devices) {
        this.device = devices;
    }


}
