package telran.multithreading;


public class PrinterControllerAppl {

	public static void main(String[] args) {
        final int N_PRINTERS = 4;
        final int N_NUMBERS = 100;
        final int N_PORTIONS = 10;

        Printer[] printers = new Printer[N_PRINTERS];

        for (int i = 0; i < N_PRINTERS; i++) {
            printers[i] = new Printer(i + 1, N_NUMBERS, N_PORTIONS);

            if (i < N_PRINTERS - 1) {
                printers[i].setNextPrinter(printers[i + 1]);
            } else {
                printers[i].setNextPrinter(printers[0]); // Circular chain
            }
        }

        printers[0].start(); // Start the printing chain

        try {
            Thread.sleep(2000); // Sleep for 2 seconds to allow all threads to start
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        printers[0].interrupt(); // Start the printing chain

        for (int i = 0; i < N_PRINTERS; i++) {
            try {
                printers[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}