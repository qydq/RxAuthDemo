package com.sunsta.demo.test;

public class DemoTest {
    //统计一段字符串中每个字符出现的次数
    //方法：直接开一个int[256]的数组，根据每个字符的ascii直接将对应的下表+1，结果就是统计出来的字符次数
    public static void main(String[] args) {
        int[] dir = new int[256];
        for (int i = 0; i < dir.length; i++) {
            dir[i] = 0;
        }
        String aims = "adfjiwfjskadfjsaifhkahfaksfagtrzdfrr234";
        char[] charArray = aims.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            int temp = (int) charArray[i];
            dir[temp] += 1;
        }
        for (int num = 0; num < 256; num++) {
            if (dir[num] != 0) {
                System.out.println((char) num + "字符串出现的次数为：" + dir[num]);
            }
        }
    }


    //再写一个冒泡排序

    public static void maoPao(int arr[]) {
        int temp;
        boolean flag;
        for (int i = 0; i < arr.length; i++) {
            flag = false;
            for (int j = arr.length - 1; j > i; j--) {
                if (arr[j] < arr[j - 1]) {
                    temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                    flag = true;
                }
            }
            if (!flag) break;
        }
    }
}
