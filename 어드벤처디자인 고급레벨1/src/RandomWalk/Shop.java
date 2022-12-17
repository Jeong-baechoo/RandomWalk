package RandomWalk;

import java.util.Random;

public class Shop {
	int boardsize = MainGUI.finalsize;
	Random randompos = new Random();			/*랜덤하게 술집 위치 생성*/
	int ShopXPos = randompos.nextInt(boardsize);
	int ShopYPos = randompos.nextInt(boardsize);
	
}
