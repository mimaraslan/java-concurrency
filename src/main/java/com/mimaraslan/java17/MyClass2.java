package com.mimaraslan.java17;

public class MyClass2 {

    public static void main(String[] args) {

     //   int a = 1 + 2;
    //    int b = a++ + 2;
    //    System.out.println(a + " " + b);


        boolean a = false, b = false;
        boolean c = b = a = !a;
        System.out.println(a + " " + b + " " + c);


    }
}
