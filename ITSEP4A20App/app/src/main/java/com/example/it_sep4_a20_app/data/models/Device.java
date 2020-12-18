package com.example.it_sep4_a20_app.data.models;

/**
 * @author Claire Zubiaurre
 */
public class Device {
    private String roomId;
    private int settingsId;
    private String name;

    public Device(){
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public int getSettingsId() {
        return settingsId;
    }

    public void setSettingsId(int settingsId) {
        this.settingsId = settingsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
