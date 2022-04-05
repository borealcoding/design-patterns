package com.kreitek.pets.controllers;

import com.kreitek.pets.Controller;
import com.kreitek.pets.Logger;
import com.kreitek.pets.domain.Cat;
import com.kreitek.pets.infraestructure.bd.DbService;

import java.util.List;

public class CatController implements Controller {

    Logger logger = Logger.getInstance();

    @Override
    public String executePut(String petName, String ownerName, String telephone) {
        logger.debug("CatController.executePut "+petName+","+ownerName+","+telephone);
        Cat cat = new Cat(petName, ownerName, telephone);
        DbService dbService = DbService.getInstance();
        dbService.addNewCat(cat);
        return "New cat has been added";
    }

    @Override
    public String executeGet() {
        logger.debug("CatController.executeGet CATS");
        DbService dbService = DbService.getInstance();
        List<Cat> cats = dbService.getCats();
        StringBuilder response = new StringBuilder();
        for (Cat cat:cats) {
            response.append(cat.toString()).append("\r\n");
        }
        return response.toString();
    }
}
