package pizzashop.service;

public class Logger {

    public void log(String message){
        System.out.println(message);
    }

    public void logError(String message){
        System.err.println(message);
    }

    public void logReportsToFile(){}

}
