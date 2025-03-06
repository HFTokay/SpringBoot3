package com.example.demo;

import java.util.Random;

public class GeneratorUtil {

    // 生成合法手机号码
    public static String phoneNumberGenerator() {
        final int[] VALID_SECOND_DIGITS = {3, 4, 5, 7, 8};
        Random random = new Random();
        StringBuilder phoneNumber = new StringBuilder("1");
        // 生成第二位
        int secondIndex = random.nextInt(VALID_SECOND_DIGITS.length);
        phoneNumber.append(VALID_SECOND_DIGITS[secondIndex]);
        // 生成剩余9位
        for (int i = 0; i < 9; i++) {
            phoneNumber.append(random.nextInt(10));
        }
        return phoneNumber.toString();
    }


    public static String chineseNameGenerator() {
        final String[] SURNAMES = {
                "赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈", "刘",
                "欧阳", "司马", "上官", "夏侯", "诸葛" // 包含少量复姓
        };
        Random random = new Random();
        StringBuilder name = new StringBuilder();

        String surname = SURNAMES[random.nextInt(SURNAMES.length)];
        name.append(surname);

        // 剩余字符生成逻辑
        int remainLength = 3 - surname.length();
        for (int i = 0; i < remainLength; i++) {
            name.append(generateCommonHanzi());
        }
        return name.toString();
    }

    // Unicode常用汉字生成‌:ml-citation{ref="1,2" data="citationList"}
    private static char generateCommonHanzi() {
        Random random = new Random();
        int codePoint = 0x4E00 + random.nextInt(0x9FA5 - 0x4E00 + 1);
        return (char) codePoint;
    }
}

