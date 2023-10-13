package org.example;

import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";

    public static void main(String[] args) {
        try {
            teskMan();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void teskMan() throws IOException {
        ArrayList<String> tasksNew = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        tasksNew.addAll(readList());
        try {
            while (true) {
                System.out.println();
                System.out.println(ANSI_GREEN + "TaskManager" + ANSI_RESET);
                System.out.println("Select an option:");
                System.out.println("1 - add task");
                System.out.println("2 - remove task");
                System.out.println("3 - show tasks");
                System.out.println(ANSI_RED + "write exit to end" + ANSI_RESET);
                String option = scan.nextLine();

                if (option.equals("1") || option.equals("add task")) {
                    System.out.println();
                    System.out.println("ID will be set automaticly ");
                    System.out.println("Insert task: ");
                    String name = scan.nextLine();
                    System.out.println("Insert deadline (format 01/01/2020): ");
                    String deadLine = scan.nextLine();
                    System.out.println("Insert priority   (true if important, false id not important):  ");
                    String priority = scan.nextLine();
                    tasksNew.add(name + " " + deadLine + " " + priority);
                } else if (option.equals("2") || option.toLowerCase().equals("remove task")) {
                    System.out.println();
                    System.out.println("Write id: ");
                    int id = scan.nextInt();
                    if (id - 1 <= readList().size()) {
                        tasksNew.remove(id - 1);
                        System.out.println("Item removed");
                        scan.nextLine();
                    } else {
                        System.out.println("Error");
                    }
                } else if (option.equals("3") || option.toLowerCase().equals("shaw tasks")) {
                    System.out.println();
                    for (int i = 0; i < tasksNew.size(); i++) {
                        System.out.println(i + 1 + ": " + tasksNew.get(i));
                    }
                } else if (option.toLowerCase().equals("exit")) {
                    System.out.println("Hasta la vista, baby");

                    String filePath = "/Users/admin/Documents/CodersLab/ONL_JEE_S_15_WorkShop/src/main/java/org/example/TaskManager.txt";
                    Path path = Paths.get(filePath);
                    Files.deleteIfExists(path);
                    Files.write(path, tasksNew);
                    System.out.println("Check your file");
                    break;
                }
            }
        } catch (InputMismatchException ex) {
            System.out.println("Please, choose correct function");
        }
    }

    public static List readList() {
        String filePath = "/Users/admin/Documents/CodersLab/ONL_JEE_S_15_WorkShop/src/main/java/org/example/TaskManager.txt";
        List<String> tasks = new ArrayList<>();
        try {
            tasks = Files.readAllLines(Paths.get(filePath));
            return tasks;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }
}