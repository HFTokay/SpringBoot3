package com.example.demo.projectlighting;

import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

public class GatewayController  {

    private String gatewayId;
    private ThreadPoolExecutor executor; // 网关专属线程池
    private List<LampGroup> managedGroups;


    // 提交灯组控制任务到线程池
    public void submitControlTask(LampGroup group) {
        executor.submit(() -> {
            if (group.getStrategy().checkCondition()) {
                String command = group.getStrategy().generateCommand();
                try {
                    sendControlCommand(group, command);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }


    // 发送指令到硬件（模拟实现）
    private void sendControlCommand(LampGroup group, String command) throws InterruptedException {
        System.out.println("分组id:" + group.getGroupId());
        System.out.println("发送指令：" + command);
        Thread.sleep(50);
        group.generateCommand(command);

    }


    public String getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(String gatewayId) {
        this.gatewayId = gatewayId;
    }

    public ThreadPoolExecutor getExecutor() {
        return executor;
    }

    public void setExecutor(ThreadPoolExecutor executor) {
        this.executor = executor;
    }

    public List<LampGroup> getManagedGroups() {
        return managedGroups;
    }

    public void setManagedGroups(List<LampGroup> managedGroups) {
        this.managedGroups = managedGroups;
    }

    public GatewayController() {
    }
}
