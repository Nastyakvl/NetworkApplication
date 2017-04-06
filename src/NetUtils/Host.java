package NetUtils;

import ConcurrentUtils.Channel;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;



/**
 * Created by 14Kavalerova on 10.02.2017.
 */
public class Host implements Runnable{

    private int port;
    private Channel channel;
    MessageHandlerFactory msghandlerFac;




    public Host(int port, Channel channel, MessageHandlerFactory msghandlerFac) {
        this.port = port;
        this.channel=channel;
        this.msghandlerFac=msghandlerFac;
    }

    public  void run() {

        try {
            ServerSocket serverSocket = new ServerSocket(port);

            while (true) {
                Socket socket = serverSocket.accept();// 'получаем' клиента
                channel.put(new Session(socket, msghandlerFac.createMessageHandler()));
                 }
        }
        catch(Exception e){
            System.out.println("Problem with arguments: "+e.getMessage());
            return;

        }

    }

}
