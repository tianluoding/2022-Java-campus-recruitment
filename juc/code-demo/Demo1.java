import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 继承Thread的多线程代码
 */
class Demo1 {
    private int flag = 1;
    public final ReentrantLock lock = new ReentrantLock();
    public final Condition condition = lock.newCondition();

    class A extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                lock.lock();
                while (flag != 1) {
                    try {
                        condition.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                        lock.unlock();
                    }
                }
                System.out.print("a");
                flag = 2;
                condition.signalAll();
                lock.unlock();
            }
        }
    }

    class B extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                lock.lock();
                while (flag != 2) {
                    try {
                        condition.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                        lock.unlock();
                    }
                }
                System.out.print("b");
                flag = 1;
                condition.signalAll();
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        Demo1 demo = new Demo1();
        A a = demo.new A();
        B b = demo.new B();
        a.start();
        b.start();

    }

}