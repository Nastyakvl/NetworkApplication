package ConcurrentUtils;

import NetUtils.Session;

/**
 * Created by Nastya on 17.03.2017.
 */
public class Dispecher implements Runnable {
    private Channel channel;
    ThreadPool threadPool;

   public Dispecher(Channel channel, ThreadPool threadPool){

        this.channel=channel;
        this.threadPool=threadPool;

    }

    @Override
    public void run() {
        while (true) {
           Session task = (Session)channel.take();
           threadPool.execute(task);
        }

    }
}
