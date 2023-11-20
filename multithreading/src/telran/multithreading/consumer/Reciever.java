package telran.multithreading.consumer;

import telran.multithreading.messaging.MessageBox;

public class Reciever extends Thread {
	private MessageBox messageBox;
	private boolean runningFlag=true;

	public Reciever(MessageBox messageBox) {
		this.messageBox = messageBox;
	}
	@Override
	public void run() {
		while(runningFlag) {
			String message = null;
			try {
				message = messageBox.take();
			} catch (InterruptedException e) {
				 Thread.currentThread().interrupt();
		            System.err.println("Receiver thread interrupted while waiting for a message.");
			}
			System.out.printf("thread id: %d, message: %s\n", getId(),message );
		}
	}
	public void SetStopFlag() {
		runningFlag=false;
	}
	
}