package controller;

import com.google.gson.Gson;
import models.MessageModel;

import java.io.IOException;
import java.net.Socket;

public class EventController {
    private static final EventController eventController = new EventController();
    private static Socket socket;
    private EventController() {}

    public static EventController getInstance() {
        return eventController;
    }

    public static void setConnection(int port, String ip) {
        try {
            socket = new Socket(ip, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendMessage(MessageModel messageModel) {
        Gson gson = new Gson();
        String res = gson.toJson(messageModel);

        System.out.println(res);
    }

}
