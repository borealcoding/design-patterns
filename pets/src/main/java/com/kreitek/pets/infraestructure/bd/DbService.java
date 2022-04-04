package com.kreitek.pets.infraestructure.bd;

import com.kreitek.pets.Logger;
import com.kreitek.pets.domain.Cat;
import com.kreitek.pets.domain.Dog;

import java.util.ArrayList;
import java.util.List;

public class DbService {
    private static volatile DbService instance = null;

    Logger logger = Logger.getInstance();
    private final List<Cat> cats = new ArrayList<>();
    private final List<Dog> dogs = new ArrayList<>();

    private DbService() {
        if (instance != null) {
            throw new RuntimeException("Usage getInstance() method to create");
        }
    }

    public static DbService getInstance() {
        if (instance == null) {
            synchronized(DbService.class) {
                if (instance == null) {
                    instance = new DbService();
                }
            }
        }
        return instance;
    }

    public void addNewDog(Dog dog) {
        dogs.add(dog);
        logger.debug("BdService.Dog added");
    }
    public void addNewCat(Cat cat) {
        cats.add(cat);
        logger.debug("BdService.Cat added");
    }

    public List<Cat> getCats() {
        logger.debug("BdService.Get " + cats.size() + " cats");
        return new ArrayList<>(cats);
    }

    public List<Dog> getDogs() {
        logger.debug("BdService.Get " + dogs.size() + " dogs");
        return new ArrayList<>(dogs);
    }
}
