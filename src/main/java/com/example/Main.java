package com.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Map<Long, String> map = new HashMap<Long, String>();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String str = scanner.nextLine();
            String s = str.split(" ")[0];
            if (s.equals("CREATE")) {
                String st = str.replaceFirst("CREATE ", "");
                System.out.println(st);
            } else if (str.equals("EXIT")) {
                return;
            } else {
                System.out.println(str);
            }

        }
    }
}