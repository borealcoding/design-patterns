package com.kreitek.pets;

public class Logger {
    private static volatile Logger instance = null;
    private int logCounter = 0;

    private Logger() {
        if(instance != null) {
            throw new IllegalStateException("Usage getInstance() method to create");
        }
    }

    public static Logger getInstance() {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    public void debug(String logMessage) {
        System.out.println("[debug]["+getLogCounter()+"] - "+logMessage);
    }

    public void incrementLogCounter() {
        logCounter++;
    }

    public int getLogCounter() {
        incrementLogCounter();
        return logCounter;
    }

}
