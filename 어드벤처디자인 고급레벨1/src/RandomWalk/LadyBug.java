package RandomWalk;

import java.util.Random;

public class LadyBug {
	int boardsize = RandomWalkAnimation.M;
	Random randompos = new Random();
	int CurrentXPos = randompos.nextInt(boardsize);
	int CurrentYPos = randompos.nextInt(boardsize);
	
	boolean eventvisite = false;
	
	boolean friendmeet = false;
}
