package telran.ThreadsRaceGame;

import java.util.ArrayList;

public class Race {
	private int distance;
	private int minSleep;
	private int maxSleep;
	private int numOfRunners;
	public long startTime;
	private Racer[]runners;
	
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	public long getStartTime() {
		return startTime;
	}
	public Race(int distance, int minSleep, int maxSleep,int numOfRunners) {
		this.distance = distance;
		this.minSleep = minSleep;
		this.maxSleep = maxSleep;
		this.numOfRunners=numOfRunners;
		this.runners=new Racer[numOfRunners];
	}
	public Racer[] getRunners() {
		return runners;
	}
	public void setRunnersByPlace(Racer runner) {
		int i=0;
		while(runners[i]!=null && i<runners.length) {
			i++;
		}
		runners[i]=runner;
	}
	public int getDistance() {
		return distance;
	}
	public int getMinSleep() {
		return minSleep;
	}
	public int getMaxSleep() {
		return maxSleep;
	}
	
}