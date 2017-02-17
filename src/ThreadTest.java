/**
 * Created by 14Kavalerova on 17.02.2017.
 */
public class ThreadTest implements Runnable{
        int threadNumber;
        public ThreadTest(int threadNumber){
            this.threadNumber=threadNumber;
        }
        public void run() {
            for (int i=0; i<5;i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread"+threadNumber);
            }

        }

}
