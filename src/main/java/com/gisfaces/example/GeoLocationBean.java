package com.gisfaces.example;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;

import com.gisfaces.event.MapGeoLocationEvent;

@ManagedBean
@SessionScoped
public class GeoLocationBean implements Serializable {

    private static final long serialVersionUID = -8866985012091642915L;

    private boolean watch;
    private boolean accuracy;
    private int timeout;
    private int maximumAge;
    private MapGeoLocationEvent event;

    public GeoLocationBean() {
        super();
        reset();
    }

    public void reset() {
        this.watch = true;
        this.accuracy = true;
        this.timeout = 60000;
        this.maximumAge = 0;
        this.event = null;
    }

    public void doGeoLocationListener(AjaxBehaviorEvent event) {
        System.out.println("Geolocation action listener fired.");

        this.event = (MapGeoLocationEvent) event;
    }

    public boolean isWatch() {
        return watch;
    }

    public void setWatch(boolean watch) {
        this.watch = watch;
    }

    public boolean isAccuracy() {
        return accuracy;
    }

    public void setAccuracy(boolean accuracy) {
        this.accuracy = accuracy;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getMaximumAge() {
        return maximumAge;
    }

    public void setMaximumAge(int maximumAge) {
        this.maximumAge = maximumAge;
    }

    public MapGeoLocationEvent getEvent() {
        return event;
    }

    public void setEvent(MapGeoLocationEvent event) {
        this.event = event;
    }
}
