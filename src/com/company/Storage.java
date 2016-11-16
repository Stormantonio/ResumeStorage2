package com.company;

import java.io.IOException;

/**
 * Created by Anton on 16.11.2016.
 */
public interface Storage {
    //CRUD

    //create
    void save(Resume r);

    void delete(String uuid) throws IOException;

    //read
    Resume get(String uuid) throws IOException;

    void update(Resume r) throws IOException;
}
