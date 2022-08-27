/**
 * Synchronized锁
 */
public class Demo3 {
    private int flag;
    public final Object lock = new Object();

    public void printA() {
        for (int i = 0; i < 10; i++) {
            synchronized (lock) {
                while (flag != 0) {
                    try {
                        lock.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                System.out.print("a");
                flag = 1;
                lock.notifyAll();
            }
        }
    }

    public void printB() {
        for (int i = 0; i < 10; i++) {
            synchronized (lock) {
                while (flag != 1) {
                    try {
                        lock.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                System.out.print("b");
                flag = 2;
                lock.notifyAll();
            }
        }
    }

    public void printC() {
        for (int i = 0; i < 10; i++) {
            synchronized (lock) {
                while (flag != 2) {
                    try {
                        lock.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                System.out.print("c");
                flag = 0;
                lock.notifyAll();
            }
        }
    }

    public static void main(String[] args) {
        Demo3 demo3 = new Demo3();
        new Thread(() -> {
            demo3.printA();
        }).start();
        ;
        new Thread(() -> {
            demo3.printB();
        }).start();
        ;
        new Thread(() -> {
            demo3.printC();
        }).start();
        ;
    }
}
