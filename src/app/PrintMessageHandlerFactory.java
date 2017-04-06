package app;

import NetUtils.MessageHandlerFactory;
import NetUtils.messageHandler;
import app.PrintMessageHandler;

/**
 * Created by Nastya on 31.03.2017.
 */
public class PrintMessageHandlerFactory implements MessageHandlerFactory {
    @Override
    public messageHandler createMessageHandler() {
        PrintMessageHandler printMsg=new PrintMessageHandler();
        return printMsg;
    }
}
