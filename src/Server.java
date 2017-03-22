import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by 14Kavalerova on 10.02.2017.
 */
public class Server {
    private static int sessionCount=0;
    private static Object lock=new Object();

    public static void main(String[] args) {
        try {
            int maxSessionCount=Integer.parseInt(args[1]);
            int port=Integer.parseInt(args[0]);


            ServerSocket serverSocket = new ServerSocket(port);
            Channel channel=new Channel(2);
            Dispecher dispecher=new Dispecher(channel);


            while (true) {

                Socket socket = serverSocket.accept(); // 'получаем' клиента

                synchronized (lock) {
                    while (sessionCount == maxSessionCount) {
                        lock.wait();
                    }

                        sessionCount++;
                        System.out.println("Count " + sessionCount);
                        Thread thread1 = new Thread(new Session(socket));
                        thread1.start();

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
