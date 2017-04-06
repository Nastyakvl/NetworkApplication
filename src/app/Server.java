package app;

import ConcurrentUtils.Channel;
import ConcurrentUtils.Dispecher;
import ConcurrentUtils.ThreadPool;
import NetUtils.Host;
import NetUtils.*;
/**
 * Created by Nastya on 23.03.2017.
 */
public class Server {
    public static void main(String[] args) {
        int maxSessionCount=Integer.parseInt(args[1]);
        int port=Integer.parseInt(args[0]);

        Class classFactory=null;

        try {
            classFactory=Class.forName(args[2]);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        MessageHandlerFactory msgHandlerFac=null;
        try {
            msgHandlerFac=(MessageHandlerFactory)classFactory.newInstance();

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        Channel channel=new Channel(maxSessionCount);
        Host host =new Host(port, channel, msgHandlerFac);
        Thread hostThread=new Thread(host);
        hostThread.start();

        ThreadPool threadPool=new ThreadPool(maxSessionCount);
        Dispecher dispecher=new Dispecher(channel, threadPool);
        Thread threadDispecher=new Thread(dispecher);
        threadDispecher.start();
    }

}
