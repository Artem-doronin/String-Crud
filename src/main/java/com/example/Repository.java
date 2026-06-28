package com.example;

import java.util.List;

public interface Repository {
    void create(Person person);

    void updateById(Person person);

    List<Person> getAll();

    Person getById(Long id);

    void deleteById(Long id);
}
