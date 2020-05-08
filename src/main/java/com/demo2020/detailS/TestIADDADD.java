package com.demo2020.detailS;

/**
 * 测试一道 i++ ++i 的面试题
 * @author xw
 * @date 2020/5/8 10:02
 */
public class TestIADDADD {

    /**
     * i++ 先赋值（使用） 在+1
     * @return
     */
    public int iAddAdd(){
        int a = 0;
        for(int i = 0; i < 99; i++){
            a = a++;
        }
        return a;
    }

    /**
     * ++i 先+1 在赋值（使用）
     * @return
     */
    public int addAddI(){
        int b = 0;
        for(int i = 0; i < 99; i++){
            b = ++b;
        }
        return b;
    }

    /**
     * i++ 后赋值 还是没搞明白？
     * @return
     */
    public String iAA_Aii(){
        //Integer a = 0;
        int a = 0;
        int b = 0;
        for(int i = 0; i < 99; i++){
            a = a++;
            b = a++;
        }
        String s = "a的值为："+ a +"，b的值为：" + b;
        return s;
    }

    public static void main(String[] args) {
        TestIADDADD testIADDADD = new TestIADDADD();
        //System.out.println("a++ = " + testIADDADD.iAddAdd());
        //System.out.println("++b = " + testIADDADD.addAddI());
        System.out.println("a=a++ or b=a++：" + testIADDADD.iAA_Aii());
    }


}
