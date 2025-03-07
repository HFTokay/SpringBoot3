package com.example.demo.projectlighting;

import java.util.List;

public class LightingSystemController {

    private List<GatewayController> gateways;


    // 启动所有网关任务
    public void startSystem() {
        gateways.forEach(gateway ->
                gateway.getManagedGroups().forEach(group -> {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            ;
                            gateway.submitControlTask(group);
                        }

                )
        );
    }


    public void shutdownSystem() {
        gateways.forEach(gateway -> gateway.getExecutor().shutdown());
    }


    public LightingSystemController() {
    }

    public LightingSystemController(List<GatewayController> gateways) {
        this.gateways = gateways;
    }
}
