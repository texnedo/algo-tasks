package com.texnedo.architecture;

import java.util.ArrayList;
import java.util.Scanner;

public class BrowserHistory {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BrowserHistory history = new BrowserHistory("ya.ru");
        while (true){
            String line = scanner.nextLine();
            String[] tokens = line.split(" ");
            if (tokens.length == 1 && "e".equals(tokens[0])) {
                break;
            } else if (tokens.length == 2 && "v".equals(tokens[0])) {
               history.visit(tokens[1]);
            } else if (tokens.length == 2 && "b".equals(tokens[0])) {
                System.out.println(history.back(Integer.parseInt(tokens[1])));
            } else if (tokens.length == 2 && "f".equals(tokens[0])) {
                System.out.println(history.forward(Integer.parseInt(tokens[1])));
            } else if (tokens.length == 1 && "h".equals(tokens[0])) {
                System.out.println(history.getHistory());
            }
        }
    }

    private final ArrayList<String> history = new ArrayList<>();
    private int currentStep = 1;
    private int lastStep = 1;

    public BrowserHistory(String homepage) {
        history.add(homepage);
    }

    public void visit(String url) {
        if (currentStep == history.size()) {
            history.add(url);
        } else {
            history.set(currentStep, url);
        }
        currentStep++;
        lastStep = currentStep;
    }

    public String back(int steps) {
        int movedBack = currentStep - steps;
        if (movedBack < 1) {
            currentStep = 1;
        } else {
            currentStep = movedBack;
        }
        return history.get(currentStep - 1);
    }

    public String forward(int steps) {
        int movedForward = currentStep + steps;
        if (movedForward > lastStep) {
            currentStep = lastStep;
        } else {
            currentStep = movedForward;
        }
        return history.get(currentStep - 1);
    }

    public ArrayList<String> getHistory() {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < currentStep; i++) {
            result.add(history.get(i));
        }
        return result;
    }
}
