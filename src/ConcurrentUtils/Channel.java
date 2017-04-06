package ConcurrentUtils;

import java.util.LinkedList;

/**
 * Created by Nastya on 17.03.2017.
 */
public class Channel {
    private final int maxCount;
    private final LinkedList<Runnable> queue=new LinkedList<Runnable>();

    private Object lock=new Object();

    public Channel(int maxCount){

        this.maxCount=maxCount;
    }

    public void put (Runnable x) {
        synchronized (lock) {
            while (queue.size() == maxCount)
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            queue.addLast(x);
            lock.notifyAll();
        }



    }

   public Runnable take(){
        synchronized (lock) {
            while (queue.size()==0) try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Runnable ret=queue.removeFirst();
            lock.notifyAll();
            return ret;
        }
    }

    public int getSize() {
        synchronized (lock) {
            return queue.size();
        }
    }
}
