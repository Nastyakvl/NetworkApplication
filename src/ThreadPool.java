import java.util.LinkedList;

/**
 * Created by Nastya on 24.03.2017.
 */
public class ThreadPool {
    private LinkedList<Runnable> allWorkers;
    private Channel freeWorkers;
    private int maxSize;
    private Object lock=new Object();

    public ThreadPool(int maxSize){

        this.maxSize=maxSize;
        allWorkers=new LinkedList<Runnable>();
        freeWorkers=new Channel(maxSize);
    }

    public void execute(Session task){
            if (freeWorkers.getSize() == 0) {
                synchronized (lock) {
                    if (allWorkers.size() < maxSize) {
                        WorkerThread worker = new WorkerThread(this);
                        allWorkers.add(worker);
                        freeWorkers.put(worker);
                    }

                }
            }
            ((WorkerThread) freeWorkers.take()).execute(task);


    }

    public void onTaskCompleted(WorkerThread worker){

            freeWorkers.put(worker);

    }

}
