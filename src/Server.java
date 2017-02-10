import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by 14Kavalerova on 10.02.2017.
 */
public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1100);
            Socket socket = serverSocket.accept(); // 'получаем' клиента
            InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);


            // DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            // BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            String msg = dataInputStream.readUTF();
            System.out.println(msg);

        }
        catch(Exception e){

        }

    }
}
