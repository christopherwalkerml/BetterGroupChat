package me.darkolythe.betterchatmanager;

public class ReturnPackage {
    
    public boolean success;
    public String message;
    
    ReturnPackage(boolean isSuccess, String newMessage) {
        success = isSuccess;
        message = newMessage;
    }
}
