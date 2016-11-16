package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Anton on 16.11.2016.
 */
public class ResumeStorage implements Storage {
    private final int storageMaxLength = 10000;
    private Resume[] storage = new Resume[storageMaxLength];
    private int size = 0;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public void save(Resume r) {
        if (isFull()) {
            return;
        }
        if (sameResumes(r.getUuid())) {
            return;
        }
        storage[size] = r;
        size++;
    }

    @Override
    public void delete(String uuid) throws IOException {
        if (isNull()) {
            return;
        }
        if (logic(uuid, getMethodName())) {
            return;
        }
        System.out.println("Резюме с идентификатором " + uuid + " не существует!");
    }

    @Override
    public Resume get(String uuid) throws IOException {
        isNull();
        if (logic(uuid, getMethodName())) {
            return new Resume(uuid);
        }
        String a = "Такого резюме " + uuid + " не существует!";
        return new Resume(a);
    }

    @Override
    public void update(Resume r) throws IOException {
        isFull();
        if (logic(r.getUuid(), getMethodName())) {
            return;
        }
        System.out.println("Такого резюме " + r + " не существует!");
    }

    private boolean isFull() {
        if (size == storageMaxLength) {
            System.out.println("Хранилище резюме переполнено!!!");
            return true;
        } else
            return false;
    }

    int size() {
        return size;
    }

    Resume[] getAll() {
        Resume[] resumes = new Resume[size];
        System.arraycopy(storage, 0, resumes, 0, size);
        return resumes;
    }

    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    private boolean sameResumes(String var) {
        for (int j = 0; j < size; j++) {
            if (storage[j].getUuid().equals(var)) {
                System.out.println("Резюме " + var + " уже есть!!!");
                return true;
            }
        }
        return false;
    }

    private boolean isNull() {
        if (size == 0) {
            System.out.println("Хранилище резюме пустое!!!");
            return true;
        }
        return false;
    }

    private boolean logic(String var, String methodName) throws IOException {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(var)) {
                switch (methodName) {
                    case "delete":
                        storage[i] = null;
                        System.out.println("Резюме с идентификатором " + var + " успешно удалено!");
                        while (storage[i] == null && storage[i + 1] != null) {
                            storage[i] = storage[i + 1];
                            storage[i + 1] = null;
                            i++;
                        }
                        size--;
                        break;
                    case "get":
                        break;
                    case "update":
                        System.out.println("Введите изменения:");
                        String newResume = reader.readLine();
                        if (sameResumes(newResume))
                            return true;
                        storage[i] = new Resume(newResume);
                        System.out.println("Резюме " + newResume + " успешно изменено!");
                        break;
                }
                return true;
            }
        }
        return false;
    }

    private static String getMethodName() {
        Exception exception = new Exception();
        return exception.getStackTrace()[1].getMethodName().toLowerCase().trim();
    }
}
