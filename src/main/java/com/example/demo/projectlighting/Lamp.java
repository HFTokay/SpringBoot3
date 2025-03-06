package com.example.demo.projectlighting;

public class Lamp {
    private String sn;       // 灯ID
    private String gatewaySn;
    private Integer status = 0;      // 状态：0（暗）/1（亮）
    private Integer brightness;  // 亮度（0-100）

    public Lamp() {
    }

    public Lamp(String gatewaySn) {
        this.gatewaySn = gatewaySn;
    }

    public Lamp(String sn, String gatewaySn) {
        this.sn = sn;
        this.gatewaySn = gatewaySn;
    }

    public Lamp(String sn, String gatewaySn, Integer status, Integer brightness) {
        this.sn = sn;
        this.gatewaySn = gatewaySn;
        this.status = status;
        this.brightness = brightness;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getGatewaySn() {
        return gatewaySn;
    }

    public void setGatewaySn(String gatewaySn) {
        this.gatewaySn = gatewaySn;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getBrightness() {
        return brightness;
    }

    public void setBrightness(Integer brightness) {
        this.brightness = brightness;
    }
}
