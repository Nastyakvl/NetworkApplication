/**
 * Created by 14Kavalerova on 17.02.2017.
 */
public class Test {
    public static void main(String[] args) {
        Thread thread1=new Thread (new ThreadTest(1));
        Thread thread2=new Thread (new ThreadTest(2));
        Thread thread3=new Thread (new ThreadTest(3));
        Thread thread4=new Thread (new ThreadTest(4));

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}
