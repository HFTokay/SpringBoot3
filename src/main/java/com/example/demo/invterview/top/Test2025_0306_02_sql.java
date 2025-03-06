package com.example.demo.invterview.top;


/**
 * SELECT
 *   CASE
 *     WHEN NULL = NULL THEN 1         //    UNKNOWN
 *     WHEN NULL <> NULL then 2        //    UNKNOWN
 *     ELSE 3
 *   END result_value                   // so  return 3
 * FROM dual;
 *
 *
 * 1 NULL = NULL 的比较结果
 * 在 SQL 中，NULL 表示“未知值”，‌任何与 NULL 的等值比较（包括 =、<>）均返回 UNKNOWN‌，而非 TRUE 或 FALSE
 *
 * 2 NULL <> NULL 的比较结果
 * 同理，NULL <> NULL 也返回 UNKNOWN，因此不会触发返回
 *
 * 3 ELSE 分支的执行
 * 所有 WHEN 条件均未满足时，最终执行 ELSE
 *
 */
public class Test2025_0306_02_sql {
    public static void main(String[] args) {

    }
}
