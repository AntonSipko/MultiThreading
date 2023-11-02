package telran.ThreadsRaceGame;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import telran.view.*;
public class RaceControllerAppl {
	ArrayList<Item>itemsList= getItems();
	private static ArrayList<Item> getItems() {
		return (ArrayList<Item>) List.of(
			Item.of("Start Race",RaceControllerAppl::startNewRace),
			Item.exit());
	}

	public static void main(String[] args) {
		InputOutput io=new SystemInputOutput();
		Menu menu=createMenu(io);
		menu.perform(io);
		

	}

	private static Menu createMenu(InputOutput io) {
		ArrayList<Item>itemsList=new ArrayList<>();
		itemsList.add(Item.of("Start New Race",io1-> startNewRace(io)));
		itemsList.add(Item.exit());
		Menu menu=new Menu("Welcome to Race Menu", itemsList);
		return menu;
	}

	private static void startNewRace(InputOutput io) {
		int numOfParticipants=io.readInt("Enter the number of Participants", "Wrong participants number",3,10);
		int distance=io.readInt("Enter the distance", "Wrong distance",100,3500);
		Race race=new Race();
		ArrayList<Racer>participants=new ArrayList<>();
		for(int i=0;i<numOfParticipants;i++) {
			participants.add(new Racer(race, i+1, distance));
		}
		participants.forEach(racer->racer.start());
		participants.forEach(racer->{
			try {
				racer.join();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			
		});
		int winner=race.getWinner();
		System.out.println("The winner is Racer #"+winner);
		
		
		
	}

	



	

}
