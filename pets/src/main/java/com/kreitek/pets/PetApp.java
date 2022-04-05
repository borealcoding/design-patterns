package com.kreitek.pets;

import com.kreitek.pets.controllers.CatController;
import com.kreitek.pets.controllers.ControllerFactory;
import com.kreitek.pets.controllers.DogController;

import java.util.Scanner;

public class PetApp {

    private static final Logger logger = Logger.getInstance();

    public static void main (String[] args) {
        ControllerFactory controllerFactory = new ControllerFactory();
        boolean end = false;
        logger.debug("Pet app has been initiated");
        while (!end) {
            String command = waitForNewCommand();
            String[] commandArgs = command.split(":");
            try {
                String response = "";
                switch (commandArgs[0]) {
                    case "end" -> end = true;
                    case "get" -> {
                        String param = getGetParam(commandArgs);
                        switch (param) {
                            case "dog" -> {
                                Controller dogController = controllerFactory.getDogController();
                                response = dogController.executeGet();
                            }
                            case "cat" -> {
                                Controller catController = controllerFactory.getCatController();
                                response = catController.executeGet();
                            }
                            default -> throw new BadCommandException();
                        }
                        System.out.println(response);
                    }
                    case "put" -> {
                        String[] params = getPutParams(commandArgs);
                        switch (params[0]) {
                            case "dog" -> {
                                Controller dogController = new DogController();
                                response = dogController.executePut(params[1], params[2], params[3]);
                            }
                            case "cat" -> {
                                Controller catController = new CatController();
                                response = catController.executePut(params[1], params[2], params[3]);
                            }
                            default -> throw new BadCommandException();
                        }
                        System.out.println(response);
                    }
                }
            } catch (BadCommandException e) {
                System.out.println("Bad command error");
            }
        }
        logger.debug("Pet app has been ended");
    }

    private static String getGetParam(String[] commandArgs) throws BadCommandException {
        if (commandArgs.length == 2) {
            return commandArgs[1];
        } else {
            throw new BadCommandException();
        }
    }

    private static String[] getPutParams(String[] commandArgs) throws BadCommandException {
        if (commandArgs.length <= 1) {
            throw new BadCommandException();
        }
        String[] parameters = commandArgs[1].split(";");
        if (parameters.length < 4) {
            throw new BadCommandException();
        }
        return parameters;
    }

    private static String waitForNewCommand() {
        System.out.print("Enter a command : ");
        Scanner scanner = new Scanner(System. in);
        return scanner. nextLine();
    }


}
