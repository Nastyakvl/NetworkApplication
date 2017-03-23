import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by 14Kavalerova on 10.02.2017.
 */
public class Server {

    private int sessionCount = 0;
    private int maxSessionCount;
    private int port;
    private Object lock = new Object();
    private Channel channel;
    private Dispecher dispecher;

    public Server(int port, int maxSessionCount) {
        this.port = port;
        this.maxSessionCount = maxSessionCount;
        channel=new Channel(2);
        dispecher=new Dispecher(channel);
    }

    public  void start() {

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Thread threadDispecehr=new Thread(dispecher);
            threadDispecehr.start();

            while (true) {
                Socket socket = serverSocket.accept();// 'получаем' клиента

                synchronized (lock) {
                    while (sessionCount == maxSessionCount) {
                        lock.wait();
                    }

                        channel.put(new Session(socket, this));
                        sessionCount++;
                        System.out.println("Count " + sessionCount);

                }

            }
        }
        catch(Exception e){
            System.out.println("Problem with arguments: "+e.getMessage());
            return;

        }

    }


    public  void threadStop(){
        synchronized (lock) {

            sessionCount--;
            System.out.println("Number of session: " + sessionCount);
            lock.notify();
        }

    }
}
