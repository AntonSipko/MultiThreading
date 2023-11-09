package telran.ThreadsRaceGame;


import java.util.ArrayList;

import telran.view.*;

public class RaceControllerAppl {

	private static final int MAX_THREADS = 10;
	private static final int MIN_THREADS = 3;
	private static final int MIN_DISTANCE = 100;
	private static final int MAX_DISTANCE = 3500;
	private static final int MIN_SLEEP = 2;
	private static final int MAX_SLEEP = 5;
	private static final int OFFSET_NUM=5;
	public static void main(String[] args) {
		InputOutput io = new SystemInputOutput();
		Item[] items = getItems();
		Menu menu = new Menu("Race Game", items);
		menu.perform(io);

	}

	private static Item[] getItems() {
		Item[] res = {
				Item.of("Start new game", RaceControllerAppl::startGame),
				Item.exit()
		};
		return res;
	}
	static void startGame(InputOutput io) {
		int nThreads = io.readInt("Enter number of the runners","Wrong number of the runners",
				MIN_THREADS, MAX_THREADS);
		int distance = io.readInt("Enter distance", "Wrong Distance",MIN_DISTANCE, MAX_DISTANCE);
		Race race = new Race(distance, MIN_SLEEP, MAX_SLEEP,nThreads);
		Racer[] runners = new Racer[nThreads];
		startRunners(runners, race);
		joinRunners(runners);
		displayRunners(race);
	}

	private static void displayRunners(Race race) {
		Racer[]runners=race.getRunners();
		System.out.println("place"+" ".repeat(3*OFFSET_NUM)+"racer number"+ " ".repeat(4*OFFSET_NUM)+" time ");
		for(int i=0;i<runners.length;i++) {
			Racer racer = runners[i];
	        if (racer != null) {
	            long time = racer.getEndTime() - race.getStartTime();
	            System.out.printf("%d" + " ".repeat(5*OFFSET_NUM) + "%d" + " ".repeat(5*OFFSET_NUM) + " %d%n", i + 1, racer.getRunnerId(), time);
	        }
	    }
		}
		

	private static void joinRunners(Racer[] runners)  {
		for(int i = 0; i < runners.length; i++) {
			try {
				runners[i].join();
			} catch (InterruptedException e) {
				
			}
		}
		
		
	}

	private static void startRunners(Racer[] runners, Race race) {
		for(int i = 0; i < runners.length; i++) {
			runners[i] = new Racer(race, i + 1);
			runners[i].start();
		}
		long startTime=System.currentTimeMillis();
		race.setStartTime(startTime);
		
		
	}

	synchronized private static long getStartTime() {
		return (long)System.currentTimeMillis();
		
	}

}