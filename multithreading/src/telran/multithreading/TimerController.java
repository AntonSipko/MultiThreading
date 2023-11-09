package telran.multithreading;

import java.time.format.DateTimeFormatter;

public class TimerController {
	public static void main(String[] args)throws InterruptedException {
		Timer timer=new Timer(DateTimeFormatter.ofPattern("HH:mm:ss"),1000);
		timer.setDaemon(true);
		timer.start();
		Thread.sleep(5000);
	}

}
