package telran.multithreading;

public class Printer extends Thread {
    private int id;
    private int N_NUMBERS;
    private int N_PORTIONS;
    private Printer nextPrinter;

    public Printer(int id, int N_NUMBERS, int N_PORTIONS) {
        this.id = id;
        this.N_NUMBERS = N_NUMBERS;
        this.N_PORTIONS = N_PORTIONS;
    }

    public void setNextPrinter(Printer nextPrinter) {
        this.nextPrinter = nextPrinter;
    }

    @Override
    public void run() {
        int startNumber = id;
        while (startNumber <= N_NUMBERS) {
            synchronized (this) {
                for (int i = 0; i < N_PORTIONS; i++) {
                    if (startNumber <= N_NUMBERS) {
                        System.out.print(".");
                        startNumber++;
                    }
                }

                if (nextPrinter != null) {
                    nextPrinter.interrupt();
                }
            }
            try {
                sleep(1000); // Sleep for 1 second
                wait();
            } catch (InterruptedException e) {
                // Thread was interrupted, continue printing
            }
        }
    }
}
