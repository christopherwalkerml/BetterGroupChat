package me.darkolythe.betterchatmanager;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {

    private String message;
    private String time;

    Message(String text) {
        message = text;
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        time = format.format(date);
    }

    public String getMessage() {
        return message;
    }

    public String getTime() {
        return time;
    }

    public String getFullMessage() {
        return "[" + time + "] " + message;
    }
}
