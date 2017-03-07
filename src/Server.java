import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by 14Kavalerova on 10.02.2017.
 */
public class Server {
    private static int sessionCount=0;
    private static int maxSessionCount=5;

    public static void main(String[] args) {


        try {
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

                //System.out.println("Count "+sessionCount);
                if (sessionCount<maxSessionCount) {
                    sessionCount++;
                    System.out.println("Count "+sessionCount);
                    Thread thread1 = new Thread(new Session(socket));
                    thread1.start();
                }
            }
        }
        catch(Exception e){

        }

    }


    public void threadStop(){
        sessionCount--;
        System.out.println("Number of session: " + sessionCount);
    }
}
