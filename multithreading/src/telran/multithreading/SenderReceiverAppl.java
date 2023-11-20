package telran.multithreading;

import java.util.ArrayList;
import java.util.List;

import telran.multithreading.consumer.Reciever;
import telran.multithreading.messaging.*;
import telran.multithreading.producer.Sender;

public class SenderReceiverAppl {

	private static final int N_MESSAGES = 20;
	private static final int N_RECEIVERS = 10;

	public static void main(String[] args) throws InterruptedException {
		MessageBox messageBox = new MessageBoxString();
		Sender sender = new Sender(messageBox, N_MESSAGES);
		sender.start();
		List<Reciever>recievers=new ArrayList<>();
		for(int i = 0; i < N_RECEIVERS; i++) {
			Reciever reciever=new Reciever(messageBox);
			reciever.start();
			recievers.add(reciever);
		}
		sender.join();
		stopRecievers(recievers);

	}

	private static void stopRecievers(List<Reciever>recievers) {
		for(Reciever reciever:recievers) {
			reciever.SetStopFlag();
			
		}
		
	}

}