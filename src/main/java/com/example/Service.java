package com.example;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public class Service {

    private final Repository repo;
    private final DataLoader dataLoader;


    public Service(Repository repo,DataLoader dataLoader) {
        this.repo = repo;
        this.dataLoader = dataLoader;
    }

    public void create(Person person) {
        repo.create(person);
    }

    public void updateById(Person person) {
        repo.updateById(person);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    public void getById(Long id) {
        System.out.println(repo.getById(id));
    }

    public void getAll() {
        List<Person> persons = repo.getAll();
        for (Person person : persons) {
            System.out.println(person);
        }
    }
    //todo вопрос если dataLoader используется в одном месте нужно ли его в поле заносить ?
    //todo у меня два dataLoader один здесь другой в InMemoryRepository в методе loadData()
    //todo у меня это разные так сказать оьекты хорошо ли это плохо ?
    public void exitToSave() throws JsonProcessingException {
        if (repo instanceof InMemoryRepository) {
            dataLoader.saveData(repo.getAll());
        }
    }
}
