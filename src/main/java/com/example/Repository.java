package com.example;

import java.util.List;

public interface Repository {
    void create(Command command);

    void updateById(Command command);

    List<Person> getAll();

    Person getById(Command command);

    void deleteById(Command command);
}
