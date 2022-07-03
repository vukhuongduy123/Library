package models;

import java.util.List;

public class MessageModel {
    private String message;
    private Object[] args = null;

    public MessageModel() {
    }

    public MessageModel(String message, Object[] args) {
        this.message = message;
        this.args = args;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}
