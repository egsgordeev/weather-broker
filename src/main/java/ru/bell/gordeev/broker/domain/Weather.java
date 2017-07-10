package ru.bell.gordeev.broker.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by EGordeev on 07.07.2017.
 */

@Entity
@Table(name="weather")
public class Weather {

    @Column(name="temperature")
    private int temperature;

    @Id
    @Column(name="city")
    private String city;

    @Column(name="timeStamp")
    private String timeStamp; //first version of the app will not parse the response date because of the YAGNI principle

    @Column(name="text")
    private String text;

    public Weather() {}

    public Weather(int temperature, String city, String timeStamp, String text) {
        this.temperature = temperature;
        this.city = city;
        this.timeStamp = timeStamp;
        this.text = text;
    }

    @Override
    public String toString() {
        return "The weather in " + city + " is " + text + "\n" + "Temperature is " + temperature + " F\n" +
                "Data were take on " + timeStamp;
    }

    public int getTemperature() {
        return temperature;
    }

    public String getCity() {
        return city;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getText() {
        return text;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setText(String text) {
        this.text = text;
    }


}
