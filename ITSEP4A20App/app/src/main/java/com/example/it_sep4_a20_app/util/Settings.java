package com.example.it_sep4_a20_app.util;

public class Settings {
    private int settingsId;
    private String lastUpdate;
    private double temperatureSetPoint;
    private int co2Min;
    private int co2Max;
    private Room room;

    public Settings() {
    }

    public Settings(int settingsId, String lastUpdate,
                    double temperatureSetPoint, int co2Min, int co2Max, Room room) {
        this.settingsId = settingsId;
        this.lastUpdate = lastUpdate;
        this.temperatureSetPoint = temperatureSetPoint;
        this.co2Min = co2Min;
        this.co2Max = co2Max;
        this.room = room;
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

    public void setCo2Min(int co2Min) {
        this.co2Min = co2Min;
    }

    public void setCo2Max(int co2Max) {
        this.co2Max = co2Max;
    }

    public int getCo2Min() {
        return co2Min;
    }

    public int getCo2Max() {
        return co2Max;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }


}
