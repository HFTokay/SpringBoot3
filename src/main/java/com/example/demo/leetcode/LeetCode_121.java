package com.example.demo.leetcode;

public class LeetCode_121 {

    public static void main(String[] args) {

       // int[] a ={4,7,1,4,9,3};

        int[] a  = {7,1,5,3,6,4};
        int max = maxProfit(a);
        System.out.println( "最大股票利益："+ max);;



    }


    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int min = prices[0];
        int max = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];
            }else if (prices[i] - min > max) {
                    max = prices[i] - min;
            }
        }
        return  max;
    }

}
