package com.example.demo.projectlighting;

public class ControlStrategyLightIntensity implements ControlStrategy {

    private double currentLux = 0.0;    // 当前光照值（从传感器获取）
    private double thresholdLux = 10.0; // 触发阈值

    @Override
    public boolean checkCondition() {
        boolean falg = currentLux < thresholdLux;
//        System.out.println("光照度阈值thresholdLux:" + thresholdLux);
//        System.out.println("光照度当前值currentLux:" + currentLux);
//        System.out.println("策略是否触发：" + falg);
        return currentLux < thresholdLux;
    }

    public double getCurrentLux() {
        return currentLux;
    }

    public void setCurrentLux(double currentLux) {
        this.currentLux = currentLux;
    }

    public double getThresholdLux() {
        return thresholdLux;
    }

    public void setThresholdLux(double thresholdLux) {
        this.thresholdLux = thresholdLux;
    }

    public ControlStrategyLightIntensity(double currentLux) {
        this.currentLux = currentLux;
    }

    public ControlStrategyLightIntensity(double currentLux, double thresholdLux) {
        this.currentLux = currentLux;
        this.thresholdLux = thresholdLux;
    }
}
