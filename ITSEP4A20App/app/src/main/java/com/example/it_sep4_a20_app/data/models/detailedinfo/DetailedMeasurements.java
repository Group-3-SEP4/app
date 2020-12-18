package com.example.it_sep4_a20_app.data.models.detailedinfo;

import java.util.ArrayList;
import java.util.List;

public class DetailedMeasurements
{
    private List<DetailedCo2> detailedCo2List;
    private List<DetailedHumidity> detailedHumidityList;
    private List<DetailedTemperature> detailedTemperatureList;

    public DetailedMeasurements()
    {
        detailedCo2List = new ArrayList<>();
        detailedHumidityList = new ArrayList<>();
        detailedTemperatureList = new ArrayList<>();
    }

    public List<DetailedCo2> getDetailedCo2List()
    {
        return detailedCo2List;
    }

    public void setDetailedCo2List(List<DetailedCo2> detailedCo2List)
    {
        this.detailedCo2List = detailedCo2List;
    }

    public List<DetailedHumidity> getDetailedHumidityList()
    {
        return detailedHumidityList;
    }

    public void setDetailedHumidityList(List<DetailedHumidity> detailedHumidityList)
    {
        this.detailedHumidityList = detailedHumidityList;
    }

    public List<DetailedTemperature> getDetailedTemperatureList()
    {
        return detailedTemperatureList;
    }

    public void setDetailedTemperatureList(List<DetailedTemperature> detailedTemperatureList)
    {
        this.detailedTemperatureList = detailedTemperatureList;
    }
}
