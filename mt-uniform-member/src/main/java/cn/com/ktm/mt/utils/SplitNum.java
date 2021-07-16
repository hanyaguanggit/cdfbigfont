package cn.com.ktm.mt.utils;

import java.util.HashSet;
import java.util.Set;


/**
 * 需求：将一个正整数拆分成若干个正整数的和，输出所有的结果不重复
 * 用递归法,比如2=1+1,3=1+(2的所有组成法),5需要分解1+4,2+3,因为3+2和2+3是一样的,for循环只要到i<=n/2就够了.
 然后就是剔除1+1+2和1+2+1的情况,继承set的特性重写了Composition(每个拆分的方式)的equals.
 懒得读取n值了,直接在main里面赋值给n。

 【此算法性能不一定很好】
 */
public class SplitNum {

    public static void main(String[] args) {
        int n = 20;//不要超过30.
        System.out.println(toStr(calc(n)));
    }

    public static Set<Composition> calc(int n) {
        Set<Composition> possibility = new HashSet<Composition>();
        Composition composition = new Composition();
        switch (n) {
            case 1:
                composition.add(1);
                possibility.add(composition);
                return possibility;
            case 2:
                composition.add(1);
                composition.add(1);
                possibility.add(composition);
                return possibility;
            default:
                for (int i = 1; i <= n / 2; i++) {
                    composition = new Composition();
                    composition.add(i);
                    composition.add(n - i);
                    possibility.add(composition);
                    if (i <= n - i) {
                        Set<Composition> partial_pos = calc(n - i);
                        for (Composition pos : partial_pos) {
                            pos.add(i);
                            possibility.add(pos);
                        }
                    }
                }
                return possibility;
        }
    }

    public static String toStr(Set<Composition> possibility) {
        String str = "total : " + possibility.size() + "\n";
        for (Composition pos : possibility)
            str += toStr(pos);
        return str;
    }

    public static String toStr(Composition composition) {
        String str = composition.get(0) + "";
        for (int i = 1; i < composition.size(); i++)
            str += (" + " + composition.get(i));
        str += "\n";
        return str;
    }
}
