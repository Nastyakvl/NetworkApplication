import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by 14Kavalerova on 17.02.2017.
 */
public class Session implements Runnable {
    Socket socket;

    Session(Socket socket){

        this.socket=socket;
    }

    public  void run(){
        Server server=new Server();
        try {
        InputStream inputStream = null;

            inputStream = socket.getInputStream();

        DataInputStream dataInputStream = new DataInputStream(inputStream);

        String msg=null;
        while(true) {
            msg = dataInputStream.readUTF();
            System.out.println(msg);
            if(msg.equals("exit")){
                System.out.println("connection close");

                 server.threadStop();
                break;
            }
        }
        }
        catch (SocketException e) {
            server.threadStop();


        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
