import java.io.*;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by 14Kavalerova on 10.02.2017.
 */
public class Client {
    public static void main(String[] args) {
        try {
            String host = args[0];
            int port = Integer.parseInt(args[1]);
            Socket socket = new Socket(host, port);
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            //   dataOutputStream.writeUTF("Hello!");

            InputStreamReader inp = new InputStreamReader(System.in);
            BufferedReader keyboard = new BufferedReader(inp);

            String line = null;

            while (true) {
                line = keyboard.readLine();
                dataOutputStream.writeUTF(line);

                if (line.equals("exit")) {
                    System.out.println("connection close");
                    break;
                }
            }


        } catch (SocketException e) {

        }
        catch (NumberFormatException e) {
            System.out.println("Problem with arguments: " + e.getMessage());
            return;
        }
        catch (IOException e){}
    }


}
