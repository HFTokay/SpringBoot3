package com.example.demo.projectlighting;

public class ControlStrategyRuleBrightDark implements ControlStrategy{

    private String ruleBrightDark = "1011";

    public String getRuleBrightDark() {
        return ruleBrightDark;
    }

    public void setRuleBrightDark(String ruleBrightDark) {
        this.ruleBrightDark = ruleBrightDark;
    }

    public ControlStrategyRuleBrightDark() {
    }



    public ControlStrategyRuleBrightDark(String ruleBrightDark) {
        this.ruleBrightDark = ruleBrightDark;
    }


    @Override
    public boolean checkCondition() {
        return true;
    }

    @Override
    public String generateCommand() {
        return ruleBrightDark;
    }
}
