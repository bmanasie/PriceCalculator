package com.redbubble;


import java.util.Scanner;

public class UserInterface {
    public static final String TEXT_RESET = "\u001B[0m";
    public static final String TEXT_BLACK = "\u001B[30m";
    public static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_GREEN = "\u001B[32m";
    public String getBasePriceFile() {

        System.out.println("Enter file path for Base Prices to proceed");
        return getUserInputFilePath();
    }

    public String getCartFile() {

        System.out.println("Enter file path for Cart to proceed");
        return getUserInputFilePath();
    }

    public int displayOptions() {
        System.out.println("Enter an option to proceed:");
        System.out.println("1: Calculate cart price");
        System.out.println("2: Enter Base Prices file again");
        System.out.println("3. Exit");

        return getUserInputOption();
    }

    public int displayInitialOptions() {
        System.out.println("Enter an option to proceed:");
        System.out.println("1: Enter Base Prices file");
        System.out.println("2: Exit");

        return getUserInputOption();
    }

    public String getUserInputFilePath() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().trim();
    }

    public void displaySuccessOutput(String message) {
        System.out.println();
        System.out.println(TEXT_GREEN + message + "\n" + TEXT_RESET);
    }
    public void displayErrorOutput(String message) {
        System.out.println();
        System.out.println(TEXT_RED + message + "\n"+ TEXT_RESET);
    }
    public void displayOutput(String message) {
        System.out.println();
        System.out.println(message + "\n");
    }
    private int getUserInputOption() {
        Scanner reader = new Scanner(System.in);
        try {
            return reader.nextInt();
        } catch (java.util.InputMismatchException e) {
            return -1;
        }
    }


}
