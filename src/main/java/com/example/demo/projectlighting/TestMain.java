package com.example.demo.projectlighting;

import java.sql.Array;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TestMain {

    public static void main(String[] args) throws InterruptedException {

        // 1. 创建网关及线程池
        GatewayController gateway1 = new GatewayController();
        gateway1.setGatewayId("GW-001");
        gateway1.setExecutor(ThreadPoolFactory.getGatewayPool("GW-001"));

        // 2. 创建灯组并绑定策略（参考‌:ml-citation{ref="3,4" data="citationList"}）
        LampGroup groupA = new LampGroup();
        groupA.setGroupId("GROUP-A");
        groupA.setLamps(initGroupLamp("GROUP-A_SN",10));
        List<ControlStrategy> strategyListA = new ArrayList<ControlStrategy>();
        strategyListA.add(new ControlStrategyRuleBrightDark("10"));
        strategyListA.add(new ControlStrategyTime(
                LocalTime.of(0,0,0),
                LocalTime.of(23,0,0)));
        strategyListA.add(new ControlStrategyLightIntensity(4.0,8));
        groupA.setStrategyList(strategyListA);
        groupA.initGroup();


        GatewayController gateway2 = new GatewayController();
        gateway2.setGatewayId("GW-002");
        gateway2.setExecutor(ThreadPoolFactory.getGatewayPool("GW-002"));

        LampGroup groupB = new LampGroup();
        groupB.setGroupId("GROUP-B");
        groupB.setLamps(initGroupLamp("GROUP-B_SN",8));
        List<ControlStrategy> strategyListB = new ArrayList<ControlStrategy>();
        strategyListB.add(new ControlStrategyRuleBrightDark("110"));
        strategyListB.add(new ControlStrategyTime(
                LocalTime.of(0,0,0),
                LocalTime.of(23,0,0)));
        strategyListB.add(new ControlStrategyLightIntensity(10,12));
        groupB.setStrategyList(strategyListB);
        groupB.initGroup();

        // 3. 独立注册灯组到网关（关键修改点）‌
        List<LampGroup> gateway1Groups = new ArrayList<>();
        gateway1Groups.add(groupA);
        gateway1.setManagedGroups(gateway1Groups);

        List<LampGroup> gateway2Groups = new ArrayList<>();
        gateway2Groups.add(groupB);
        gateway2.setManagedGroups(gateway2Groups);

        // 4. 构建系统控制器并启动‌:ml-citation{ref="5,6" data="citationList"}
        List<GatewayController> allGateways = new ArrayList<>();
        allGateways.add(gateway1);
        allGateways.add(gateway2);
        new LightingSystemController(allGateways).startSystem();

        // 4. 可选：监控线程池状态
        ThreadPoolFactory.logPoolStatus("GW-001");
        ThreadPoolFactory.logPoolStatus("GW-002");

    }


    public static List<Lamp> initGroupLamp(String lamPrefix,int lamSum) {

        List<Lamp> list = new ArrayList<>();
        for (int i = 1; i <= lamSum; i++) {
            Lamp lamp = new Lamp();
            lamp.setSn(lamPrefix + i);
            list.add(lamp);
        }
        return list;
    }
}
