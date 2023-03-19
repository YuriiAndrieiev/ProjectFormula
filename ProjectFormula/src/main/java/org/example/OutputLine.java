package org.example;

import java.time.Duration;


public class OutputLine {

    private String racer;
    private String car;
    private Duration timeLoop;

    public OutputLine(String racer, String car, Duration timeLoop) {
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
    public String toString() {
        return "ConsoleLine{" +
                "racer='" + racer + '\'' +
                ", car='" + car + '\'' +
                ", timeLoop=" + timeLoop +
                '}';
    }
}
