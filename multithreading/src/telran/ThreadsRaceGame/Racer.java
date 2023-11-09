package telran.ThreadsRaceGame;

import java.time.LocalTime;

public class Racer extends Thread {
private Race race;
private int runnerId;
public long endTime;
public Racer(Race race,int runnerId) {
	this.race=race;
	this.runnerId=runnerId;
}

public long getEndTime() {
	return endTime;
}

public int getRunnerId() {
	return runnerId;
}

@Override
public void run() {
	int sleepRange = race.getMaxSleep() - race.getMinSleep() + 1;
	int minSleep = race.getMinSleep();
	int distance = race.getDistance();
	for (int i = 0; i < distance; i++) {
		try {
			sleep((long) (minSleep + Math.random() * sleepRange));
		} catch (InterruptedException e) {
			
		}
		System.out.printf("%d - step %d\n",runnerId, i);
	}
	endTime=System.currentTimeMillis();
	race.setRunnersByPlace(this);
}
}