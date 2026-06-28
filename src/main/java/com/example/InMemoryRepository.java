package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryRepository implements Repository {

    private final Map<Long, Person> map;

    public InMemoryRepository() {
        this.map = new HashMap<>();
        loadData();
    }

    @Override
    public void create(Person person) {
        Long id = getNextId();
        Person newPerson = new Person(
                id,
                person.getName(),
                person.getAge()
        );
        map.put(id, newPerson);
        System.out.println("Person saved with id = " + id);
    }

    @Override
    public void updateById(Person person) {
        map.put(person.getId(), person);
        System.out.println("Person with id = " + person.getId() + "updated");
    }

    @Override
    public List<Person> getAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public Person getById(Long id) {
        return map.get(id);
    }

    @Override
    public void deleteById(Long id) {
        map.remove(id);
        System.out.println("Person with id = " + id + " deleted");
    }

    private long getNextId() {
        return map.keySet()
                .stream().max(Long::compareTo)
                .orElse(0L) + 1;
    }

    private Map<Long, Person> listToMap(List<Person> list) {
        return list.stream().collect(Collectors.toMap(Person::getId, p -> p));
    }

    private void loadData() {
        DataLoader dataLoader = new DataLoader();
        List<Person> loaded = dataLoader.loadData();
        if (loaded != null && !loaded.isEmpty()) {
            Map<Long, Person> loadedMap = listToMap(loaded);
            map.putAll(loadedMap);
            System.out.println("✓ Загружено " + loaded.size() + " записей");
        } else {
            System.out.println("✓ Новое хранилище (данных нет)");
        }
    }
}
