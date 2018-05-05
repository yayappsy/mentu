package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * @Author cwl [942057398@qq.com]
 * @Date 2018/4/16 20:10
 */
public class Test {


    public static int aMethod(int i) throws Exception {
        try {
            return i / 18;
        } catch (Exception ex) {
            throw new Exception("exception in a aMothod");
        } finally {
            System.err.print("finally");
        }
    }
    static  int[] b = new int[5];
    public static void main(String[] args) {
        int i = 0, s = 8;
        do {
            if (i % 2 == 0) {
                i++;
                continue;
                i++;
                s = s + i;
            }

        }
        while (i < 7) ;
        System.out.println(s);

    }
}
