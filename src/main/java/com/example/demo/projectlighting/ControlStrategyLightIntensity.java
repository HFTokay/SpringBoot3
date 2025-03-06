package com.example.demo.projectlighting;

public class ControlStrategyLightIntensity implements ControlStrategy {

    private double currentLux;    // 当前光照值（从传感器获取）
    private double thresholdLux = 10.0; // 触发阈值

    @Override
    public boolean checkCondition() {
        return currentLux < thresholdLux;
    }

}
