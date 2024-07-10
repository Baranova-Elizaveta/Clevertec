package ru.clevertec.check;

public class ExceptionHandler {
    public static void handle(Exception e, String message) {
        System.err.println(message + ": " + e.getMessage());
    }
}
