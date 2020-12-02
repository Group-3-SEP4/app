package com.example.it_sep4_a20_app.util.objects;

import java.util.List;

public class Settings {
    private int settingsId;
    private String lastUpdate;
    private double temperatureSetPoint;
    private int ppmMin;
    private int ppmMax;
    private List<Room> room;

    public Settings() {
    }

    public Settings(int settingsId, String lastUpdate,
                    double temperatureSetPoint, int co2Min, int co2Max, List<Room> rooms) {
        this.settingsId = settingsId;
        this.lastUpdate = lastUpdate;
        this.temperatureSetPoint = temperatureSetPoint;
        this.ppmMin = co2Min;
        this.ppmMax = co2Max;
        this.room = rooms;
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

    public double getTemperatureSetPoint() {
        return temperatureSetPoint;
    }

    public void setTemperatureSetPoint(double temperatureSetPoint) {
        this.temperatureSetPoint = temperatureSetPoint;
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

    public List<Room> getRooms() {
        return room;
    }

    public void setRooms(List<Room> rooms) {
        this.room = rooms;
    }


}
