package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private final static ResumeStorage ARRAY_STORAGE = new ResumeStorage();

    public static void main(String[] args) throws IOException {
        // write your code here
//        ResumeStorage resumeStorage = new ResumeStorage();
//        Resume resum = new Resume("1");
//        resumeStorage.save(resum);
//        Resume resum1 = new Resume("2");
//        resumeStorage.save(resum1);
//        Resume resum2 = new Resume("3");
//        resumeStorage.save(resum2);
//        Resume[] resumes = resumeStorage.getAll();
//        for (Resume r: resumes) {
//            System.out.println(r);
//        }

        System.out.println("Size: " + ARRAY_STORAGE.size());
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            try {
                System.out.print("Введите одну из команд - (list | save uuid | delete uuid | get uuid | update uuid | clear | exit): ");
                String[] params = reader.readLine().trim().toLowerCase().split(" ");
                if (params.length < 1 || params.length > 2) {
                    System.out.println("Неверная команда.");
                    continue;
                }
                if (params.length == 2) {
                    params[1] = params[1].intern();
                }
                switch (params[0]) {
                    case "list":
                        printAll();
                        break;
                    case "size":
                        System.out.println(ARRAY_STORAGE.size());
                        break;
                    case "save":
                        Resume r = new Resume(params[1]);
                        ARRAY_STORAGE.save(r);
                        printAll();
                        break;
                    case "delete":
                        ARRAY_STORAGE.delete(params[1]);
                        printAll();
                        break;
                    case "update":
                        r = new Resume(params[1]);
                        ARRAY_STORAGE.update(r);
                        printAll();
                        break;
                    case "get":
                        System.out.println(ARRAY_STORAGE.get(params[1]));
                        break;
                    case "clear":
                        ARRAY_STORAGE.clear();
                        printAll();
                        break;
                    case "exit":
                        return;
                    default:
                        System.out.println("Неверная команда.");
                        break;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("При вводе команды необходимо через пробел дописывать uuid. Попробуйте еще раз!");
            }
        }
    }

    private static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}

