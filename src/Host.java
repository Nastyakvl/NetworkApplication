/**
 * Created by Nastya on 23.03.2017.
 */
public class Host {
    public static void main(String[] args) {
        int maxSessionCount=Integer.parseInt(args[1]);
        int port=Integer.parseInt(args[0]);


        Channel channel=new Channel(maxSessionCount);
        Server server=new Server(port, channel);
        Thread serverThread=new Thread(server);
        serverThread.start();

        ThreadPool threadPool=new ThreadPool(maxSessionCount);
        Dispecher dispecher=new Dispecher(channel, threadPool);
        Thread threadDispecher=new Thread(dispecher);
        threadDispecher.start();
    }

}
