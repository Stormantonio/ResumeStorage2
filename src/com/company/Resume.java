package com.company;

/**
 * Created by Anton on 16.11.2016.
 */
public class Resume {
    // unique identifier
    private String uuid;
//    private String fio;
//    private boolean sex;
//    private int age;


    public Resume(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public String toString() {
        return uuid;
    }
}
