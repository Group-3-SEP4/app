package com.example.it_sep4_a20_app.data.models.detailedinfo;

public class DetailedTemperature
{
    private String timestamp;
    private double value;

    public DetailedTemperature(String timestamp, double value)
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

    public double getValue()
    {
        return value;
    }

    public void setValue(double value)
    {
        this.value = value;
    }
}
