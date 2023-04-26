package org.example;

import java.time.Duration;
import java.util.Objects;

public class Racer {

    private String racer;
    private String car;
    private Duration timeLoop;

    public Racer(String racer, String car, Duration timeLoop) {
        this.racer = racer;
        this.car = car;
        this.timeLoop = timeLoop;
    }

    public String getRacer() {
        return racer;
    }

    public String getCar() {
        return car;
    }

    public Duration getTimeLoop() {
        return timeLoop;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Racer)) return false;
        Racer racer1 = (Racer) o;
        return getRacer().equals(racer1.getRacer()) && getCar().equals(racer1.getCar()) && getTimeLoop().equals(racer1.getTimeLoop());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRacer(), getCar(), getTimeLoop());
    }

    @Override
    public String toString() {
        return "Racer{" +
                "racer='" + racer + '\'' +
                ", car='" + car + '\'' +
                ", timeLoop=" + timeLoop +
                '}';
    }
}
