package app;

import NetUtils.messageHandler;

/**
 * Created by Nastya on 31.03.2017.
 */
public class PrintMessageHandler implements messageHandler {
    @Override
    public String handle(String msg) {
        System.out.println(msg);
        return "";
    }
}
