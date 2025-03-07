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
        groupA.setLamps(initLampA());
        groupA.setStrategy(new ControlStrategyRuleBrightDark("10"));
        groupA.initGroup();


        GatewayController gateway2 = new GatewayController();
        gateway2.setGatewayId("GW-002");
        gateway2.setExecutor(ThreadPoolFactory.getGatewayPool("GW-002"));

        LampGroup groupB = new LampGroup();
        groupB.setGroupId("GROUP-B");
        groupB.setLamps(initLampB());
        groupB.setStrategy(new ControlStrategyRuleBrightDark("110"));
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

//        while (true){
//            Thread.sleep(500);
//            ThreadPoolFactory.logPoolStatus("GW-001");
//            ThreadPoolFactory.logPoolStatus("GW-002");
//        }


//        List lampGroups = new ArrayList<LampGroup>();
//        gateway1.setManagedGroups(lampGroups);
//        gateway2.setManagedGroups(lampGroups);
//
//        // 3. 注册灯组到网关
//        gateway1.getManagedGroups().add(groupA);
//        gateway2.getManagedGroups().add(groupB);
//
//        List<GatewayController> allList = new ArrayList<GatewayController>();
//        allList.add(gateway1);
//        allList.add(gateway2);
//
//        // 4. 启动系统
//        new LightingSystemController(allList).startSystem();

    }


    public static List initLampA() {

        List<Lamp> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Lamp lamp = new Lamp();
            lamp.setSn("lamA_sn" + i);
            list.add(lamp);
        }
        return list;

    }


    private static List<Lamp> initLampB() {
        List<Lamp> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Lamp lamp = new Lamp();
            lamp.setSn("lamB_sn" + i);
            list.add(lamp);
        }
        return list;
    }
}
