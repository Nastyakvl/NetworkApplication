package ConcurrentUtils;

import ConcurrentUtils.ThreadPool;
import NetUtils.Session;

/**
 * Created by Nastya on 24.03.2017.
 */
public class WorkerThread implements Runnable {
    private Thread thread;
    private ThreadPool threadPool;
    private Session currentTask;
    private Object lock=new Object();


    public WorkerThread(ThreadPool threadPool) {
        this.threadPool=threadPool;
        thread=new Thread(this);
        currentTask=null;
        thread.start();
}

    public void execute(Session task) {
        synchronized (lock) {
            if(currentTask!=null)
                throw new IllegalStateException("Current task is not null");
            currentTask = task;
            lock.notifyAll();
        }
    }


    @Override
    public void run() {
        while (true){
            synchronized (lock) {

                while (currentTask == null)
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                try {
                    currentTask.run();
                }
                catch(RuntimeException e){
                    e.getStackTrace();
                }
                finally {
                    currentTask = null;
                    threadPool.onTaskCompleted(this);
                }

            }
        }

    }

}
