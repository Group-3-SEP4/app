package com.example.it_sep4_a20_app.data.models.detailedinfo;

public class DetailedHumidity
{
    private String timestamp;
    private int value;

    public DetailedHumidity(String timestamp, int value)
    {
        this.timestamp = timestamp;
        this.value = value;
    }

    public String getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(String timestamp)
    {
        this.timestamp = timestamp;
    }

    public int getValue()
    {
        return value;
    }

    public void setValue(int value)
    {
        this.value = value;
    }
}
