package com.example.demo.projectlighting;

import java.time.LocalTime;

public class ControlStrategyTime implements ControlStrategy{
   private double threshold = 10.0; // 光照阈值

    private LocalTime startTime;
    private LocalTime endTime;

    @Override
    public boolean checkCondition() {
        LocalTime now = LocalTime.now();
        return now.isAfter(startTime) && now.isBefore(endTime);
    }


    public ControlStrategyTime() {
    }

    public ControlStrategyTime(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public ControlStrategyTime(double threshold, LocalTime startTime, LocalTime endTime) {
        this.threshold = threshold;
        this.startTime = startTime;
        this.endTime = endTime;
    }



}
