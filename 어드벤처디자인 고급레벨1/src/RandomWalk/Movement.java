package RandomWalk;

import java.util.Iterator;
import java.util.Random;

public class Movement {
	static int Xdirection = 0;
	static int Ydirection = 0;
	
	static Boolean move(LadyBug bug, int size) { // 무당벌레 움직임
		Random randomMove = new Random();
		RandomWalkDemo.field[bug.CurrentXPos][bug.CurrentYPos] = 1; //움직이기 전 현재위치 1으로 셋팅
		LadyBug currentlocationBug = new LadyBug();
		
		currentlocationBug.CurrentXPos = bug.CurrentXPos; 
		currentlocationBug.CurrentYPos = bug.CurrentYPos;
		
		do {
			Xdirection = randomMove.nextInt(3) - 1; // -1 ~ 1;
			Ydirection = randomMove.nextInt(3) - 1; // -1 ~ 1;

		} while (Xdirection == 0 && Ydirection == 0); // 제자리 불가
		bug.CurrentXPos = bug.CurrentXPos + Xdirection;
		bug.CurrentYPos = bug.CurrentYPos + Ydirection;
		if(!Check(bug, currentlocationBug, size)) {
			move(bug, size);
		}
		return true;

	}
	static Boolean Check(LadyBug movingBug, LadyBug currentlocationBug, int size) { // 벽에 부딪히는 경우 예외처
		if(movingBug.CurrentXPos >= 0 && movingBug.CurrentYPos >= 0 && movingBug.CurrentXPos < size && movingBug.CurrentYPos < size) {
			return true;
		}
		else {
			movingBug.CurrentXPos = currentlocationBug.CurrentXPos;
			movingBug.CurrentYPos = currentlocationBug.CurrentYPos;
			return false;
		}
	}
}
