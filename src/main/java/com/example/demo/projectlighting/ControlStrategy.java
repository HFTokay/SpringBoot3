package com.example.demo.projectlighting;

public interface ControlStrategy {
    default boolean checkCondition()// 检查策略是否满足
    {
        return false;
    }


    default String generateCommand()// 生成控制指令（如 "110" 表示亮亮暗）
    {
        return null;
    }

}
