package com.mimaraslan.java17;

public class MyClass {

    public static void main(String[] args) {

        String a = "1" + 2;
        String b = String.valueOf(1) + 2;

        System.out.println(a == b);
        System.out.println(a.equals(b));

        String c = "12";
        String d = String.valueOf(12);
      //  System.out.println(c == d);

    }
}
