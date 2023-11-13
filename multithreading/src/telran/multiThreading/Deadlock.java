package telran.multiThreading;
public class Deadlock {
    static class SharedResource {
        synchronized void method1(SharedResource other) {
            System.out.println(Thread.currentThread().getName() + " is executing method1");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            other.method2(this);
        }

        synchronized void method2(SharedResource other) {
            System.out.println(Thread.currentThread().getName() + " is executing method2");
            try {
                Thread.sleep(100); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            other.method1(this);
        }
    }

    public static void main(String[] args) {
        final SharedResource resource1 = new SharedResource();
        final SharedResource resource2 = new SharedResource();

        Thread thread1 = new Thread(() -> resource1.method1(resource2));
        Thread thread2 = new Thread(() -> resource2.method1(resource1));

        thread1.start();
        thread2.start();
    }
}
