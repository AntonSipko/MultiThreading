package telran.ThreadsRaceGame;
import java.util.Random;

public class Racer extends Thread {
	private final Race race;
	private final int racerNumber;
	private final int distance;
	public Racer(Race race,int racerNumber,int distance) {
		this.race=race;
		this.racerNumber=racerNumber;
		this.distance=distance;
	}
	@Override
	public void run() {
		Random random=new Random();
		for(int i=0;i<distance;i++) {
			System.out.println("Racer#"+racerNumber);
			try {
				Thread.sleep(random.nextLong(4)+2);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		if(race.getWinner()==-1) {
			race.setWinner(racerNumber);
		}
		
		
		
	}
	

}
