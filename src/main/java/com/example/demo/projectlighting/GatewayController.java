package com.example.demo.projectlighting;


import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class GatewayController {

    private String gatewayId;
    private ThreadPoolExecutor executor; // 网关专属线程池
    private List<LampGroup> managedGroups;

    public int randomValue(int min, int max) {
        // 生成 [nin,nax] 的随机整数
        return (int) (Math.random() * (max - min + 1)) + min;
    }


    public void initRandomValueLux() throws InterruptedException {
        while (true) {
            double currentLux = randomValue(5, 15);
            Thread.sleep(5000);
            for (LampGroup lampGroup : managedGroups) {
                List<ControlStrategy> strategyList = lampGroup.getStrategyList();
                for (ControlStrategy strategy : strategyList) {
                    if (strategy instanceof ControlStrategyLightIntensity) {
                        double randomValueCurrentLux = ((ControlStrategyLightIntensity) strategy).getCurrentLux();
                        if (randomValueCurrentLux == currentLux) {

                        } else {
                            submitControlTask(lampGroup);
                        }
                    }
                }
            }
        }
    }


    // 新增方法：持续监控光照强度并控制任务提交
    public void startLightIntensityMonitoring(LampGroup group) {
        executor.submit(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    // 遍历策略列表，获取光照强度值
                    for (ControlStrategy strategy : group.getStrategyList()) {
                        if (strategy instanceof ControlStrategyLightIntensity) {
                            double thresholdLux = ((ControlStrategyLightIntensity) strategy).getThresholdLux();
                            int currentLux = randomValue(5, 15);
                            System.out.println("当前光照强度: " + currentLux);

                            // 根据阈值决定是否提交任务
                            if (thresholdLux > currentLux) {  // 假设阈值为100 lux
                                System.out.println("光照强度达标，提交控制任务组: " +group.getGroupId());
                                submitControlTask(group);  // 调用原任务提交方法
                            } else {
                                // System.out.println("光照强度不足，暂停任务提交：" + group.getGroupId());
                            }
                        }
                    }
                    Thread.sleep(9000);  // 每秒检查一次，避免高频占用资源‌:ml-citation{ref="6" data="citationList"}
                } catch (InterruptedException e) {
                    System.err.println("监控线程中断: " + e.getMessage());
                    Thread.currentThread().interrupt();  // 恢复中断状态‌:ml-citation{ref="2" data="citationList"}
                }
            }
        });
    }



    // 提交灯组控制任务到线程池
    public void submitControlTask(LampGroup group) {
        executor.submit(() -> {
            try {
                String command = "";
                boolean flag = false;
                List<ControlStrategy> strategyList = group.getStrategyList();
                ArrayList<Boolean> flagCommitList = new ArrayList<Boolean>();
                for (ControlStrategy strategy : strategyList) {
                    if (strategy instanceof ControlStrategyRuleBrightDark) {
                        if (strategy.checkCondition()) {
                            //光照灯亮暗排序策略 亮暗
                            command = strategy.generateCommand();
                            System.out.println("灯的亮暗排序: "+command);
                            log.info("灯的亮暗排序:{} ", command);
                            log.error("灯的亮暗排序:{} ", command);
                        }
                    } else {
                        System.out.println("strategy.checkCondition(): " + strategy.checkCondition());
                        flagCommitList.add(strategy.checkCondition());
                    }
                }

                System.out.println("flagCommitList :" + flagCommitList);
                for (Boolean flagBoolean : flagCommitList) {
                    if (flagBoolean) {
                        flag = true;
                    } else {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    try {
                        sendControlCommand(group, command.toString());
                    } catch (InterruptedException e) {
                        System.out.println("e.toString():" + e.toString());
                        System.out.println("e.getMessage():" + e.getMessage());
                        throw new RuntimeException(e);
                    }
                }
            } catch (Exception e) {
                System.err.println("异步任务出错: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }

//    public void submitControlTask(LampGroup group) {
//        //CompletableFuture 可以在线程池打印信息
//        CompletableFuture.runAsync(() -> {
//            List<ControlStrategy> strategyList = group.getStrategyList();
//            ArrayList<Boolean> flagCommitList = new ArrayList<Boolean>();
//
//            String command = "";
//            boolean flag = false;
//            for (ControlStrategy strategy : strategyList) {
//                if (strategy instanceof ControlStrategyRuleBrightDark) {
//                    if (strategy.checkCondition()) {
//                        //光照灯亮暗排序策略 亮暗
//                        command = strategy.generateCommand();
//                        System.out.println("灯亮暗排序: " + strategy.generateCommand());
//                    } else {
//                        System.out.println("strategy.checkCondition(): " + strategy.checkCondition());
//                        flagCommitList.add(strategy.checkCondition());
//                    }
//                }
//            }
//
//            //判断整个策略组 最后是否满足条件
//            for (Boolean flagBoolean : flagCommitList) {
//                if (flagBoolean) {
//                    flag = true;
//                } else {
//                    flag = false;
//                    break;
//                }
//            }
//
//            if (flag) {
//                try {
//                    sendControlCommand(group, command);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }, executor).exceptionally(e -> {
//            System.out.println("e.toString():" + e.toString());
//            System.out.println("e.getMessage():" + e.getMessage());
//            return null;
//        });
//    }


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
