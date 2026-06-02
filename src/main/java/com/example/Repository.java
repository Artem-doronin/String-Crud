package com.example;

import java.util.HashMap;
import java.util.Map;

public class Repository {
    Map<Long,String> map = new HashMap<Long,String>();

    public void create(Command command) {
        map.put(command.getId(), command.getValue());
        System.out.println("String saved with id = {"+command.getId()+"}");
    }
}
