package com.example.demo.projectlighting;

import com.rabbitmq.client.BlockedCallback;
import lombok.Synchronized;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

public class LampGroup {

    private String groupId;
    private List<Lamp> lamps;        // 按顺序存储的灯列表
    private ControlStrategy strategy;// 当前生效策略
    private transient ThreadPoolExecutor executor;

    // 初始化组内灯状态（默认全暗）
    public void initGroup() {
        lamps.forEach(lamp -> lamp.setStatus(0));
    }


    // 根据灯顺序生成指令（参考‌:ml-citation{ref="3,5"}）
    public String generateCommand(String rulePattern) {

        List list = new ArrayList<String>();

        StringBuilder cmd = new StringBuilder();
        // 规则有效性校验（空规则返回空指令）‌
        if (rulePattern == null || rulePattern.isEmpty()) return "";

        // 转换为字符数组（规则循环应用）
        char[] rule = rulePattern.toCharArray();
        int ruleLength = rule.length;

        // 遍历灯具并应用规则
        for (int i = 0; i < lamps.size(); i++) {
            Lamp lamp = lamps.get(i);
            // 根据规则索引计算状态‌
            int statusIndex = i % ruleLength;
            char statusChar = rule[statusIndex];
            // 字符转数字（'1' -> 1, '0' -> 0）‌
            int status = Character.getNumericValue(statusChar);

            // 更新灯状态并记录日志
            lamp.setStatus(status);
            cmd.append(status);

            // 打印SN与状态（调试用）
            System.out.printf("LampSN: %s | LampStatus: %d%n", lamp.getSn(), status);

            if (1 == status){
                list.add(lamp.getSn());
            }
        }

        System.out.println("亮灯集合："+list.toString());
        System.out.println("---------------------------------------------");
        return cmd.toString();
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public List<Lamp> getLamps() {
        return lamps;
    }

    public void setLamps(List<Lamp> lamps) {
        this.lamps = lamps;
    }

    public ControlStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(ControlStrategy strategy) {
        this.strategy = strategy;
    }

    public ThreadPoolExecutor getExecutor() {
        return executor;
    }

    public void setExecutor(ThreadPoolExecutor executor) {
        this.executor = executor;
    }
}
