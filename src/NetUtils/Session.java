package NetUtils;

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
    messageHandler messageHandler;

    Session(Socket socket, messageHandler handler){

        this.socket=socket;
        this.messageHandler=handler;
    }

    public  void run(){
        //Host server=new Host();
        try {
            InputStream inputStream = null;

            inputStream = socket.getInputStream();

            DataInputStream dataInputStream = new DataInputStream(inputStream);

            String msg=null;
             while(true) {

                msg = dataInputStream.readUTF();

            if(msg.equals("exit")){
                System.out.println("connection close");
                break;
            }

                 messageHandler.handle(msg);


            }
        }
        catch (SocketException e){
            System.out.println("connection close");
        }
        catch (IOException e) {
           // System.out.println("connection close");
        }
        finally{


       }

    }
}
