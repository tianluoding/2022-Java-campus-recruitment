import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo {
    private static ExecutorService pool;

    public static void printA() {
        System.out.print("A");
    }

    public static void printB() {
        System.out.print("B");
    }

    public static void printC() {
        System.out.print("C");
    }

    public static void main(String[] args) {
        pool = new ThreadPoolExecutor(
                2, 4,
                1000, TimeUnit.MILLISECONDS,
                new SynchronousQueue<Runnable>(),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        while(true){
            pool.execute(()->{
                printA();
            });
            pool.execute(()->{
                printB();
            });
            pool.execute(()->{
                printC();
            });
        }
        
    }
}
