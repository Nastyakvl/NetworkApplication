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

            /*InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);


            // DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            // BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String msg=null;
            while(true) {
                msg = dataInputStream.readUTF();
                System.out.println(msg);
                if(msg.equals("exit")){
                    System.out.println("connection close");
                    break;
                }
            }*/


            while (true) {

                Socket socket = serverSocket.accept(); // 'получаем' клиента

                    synchronized (lock) {
                        if (sessionCount < maxSessionCount) {
                            sessionCount++;
                            System.out.println("Count " + sessionCount);
                            Thread thread1 = new Thread(new Session(socket));
                            thread1.start();
                           // DataOutputStream dos = new DataOutputStream(socket.getOutputStream()); //? do not work
                          //  dos.writeUTF("Hello");

                        } else {

                          //  DataOutputStream dos = new DataOutputStream(socket.getOutputStream()); //? do not work
                          //  dos.writeUTF("Sorry, server too busy. Please try later");
                           // socket.close();
                            lock.wait();
                        }
                   }


                //System.out.println("Count "+sessionCount);
                /*if (sessionCount<maxSessionCount) {
                    sessionCount++;
                    System.out.println("Count "+sessionCount);
                    Thread thread1 = new Thread(new Session(socket));
                    thread1.start();
                }*/
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
            lock.notify();
            System.out.println("Number of session: " + sessionCount);
        }

    }
}
