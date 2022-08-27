import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 实现Runnable接口的匿名内部类，按顺序打印abc
 */
public class Demo2 {
    private int flag;
    public final ReentrantLock lock = new ReentrantLock();
    public final Condition condition = lock.newCondition();

    public void printA() {
        for (int i = 0; i < 10; i++) {
            lock.lock();
            while (flag != 0) {
                try {
                    condition.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.print("a");
            flag = 1;
            condition.signalAll();
            lock.unlock();
        }
    }

    public void printB() {
        for (int i = 0; i < 10; i++) {
            lock.lock();
            while (flag != 1) {
                try {
                    condition.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.print("b");
            flag = 2;
            condition.signalAll();
            lock.unlock();
        }
    }

    public void printC() {
        for (int i = 0; i < 10; i++) {
            lock.lock();
            while (flag != 2) {
                try {
                    condition.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.print("c");
            flag = 0;
            condition.signalAll();
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Demo2 demo2 = new Demo2();
        new Thread(() -> {
            demo2.printA();
        }).start();
        new Thread(() -> {
            demo2.printB();
        }).start();
        new Thread(() -> {
            demo2.printC();
        }).start();
    }

}
