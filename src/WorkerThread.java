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


                currentTask.run();
                currentTask = null;
                threadPool.onTaskCompleted(this);

            }
        }

    }

}
