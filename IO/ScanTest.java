package com.test;

import java.util.Scanner;

public class ScanTest {

    public static boolean Judge(int num){
        if(num == 1)
            return false;
        for(int i = 2; i <= Math.sqrt(num); i++){
            if(num % i == 0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
            boolean result = Judge(num);
            if (result == true)
                System.out.println("是素数");
            else
                System.out.println("不是素数");
    }
}
