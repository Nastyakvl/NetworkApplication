import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by 14Kavalerova on 10.02.2017.
 */
public class Server implements Runnable{

    private int port;
    private Channel channel;


    public Server(int port, Channel channel) {
        this.port = port;
        this.channel=channel;
    }

    public  void run() {

        try {
            ServerSocket serverSocket = new ServerSocket(port);

            while (true) {
                Socket socket = serverSocket.accept();// 'получаем' клиента
                channel.put(new Session(socket));
                 }
        }
        catch(Exception e){
            System.out.println("Problem with arguments: "+e.getMessage());
            return;

        }

    }

}
