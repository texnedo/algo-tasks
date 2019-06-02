package com.texnedo.architecture;


import java.util.Scanner;

public class IntervalManagerRunner {
    public static void main(String[] args) {
        IntervalManager manager = new IntervalManager();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String id = scanner.nextLine();
            int rollout = scanner.nextInt();
            manager.setMessageRollout(id, rollout);
            manager.printAllRollouts();
            scanner.nextLine();
        }
    }
}
