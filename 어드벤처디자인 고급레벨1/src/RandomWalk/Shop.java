package RandomWalk;

import java.util.Random;

public class Shop {
	int boardsize = RandomWalkAnimation.M;
	Random randompos = new Random();
	int ShopXPos = randompos.nextInt(boardsize);
	int ShopYPos = randompos.nextInt(boardsize);
	
	boolean bugvisite = false;
}
