package RandomWalk;

import java.awt.Color;
import java.util.Random;

import javax.swing.JLabel;

public class LadyBug {
	int boardsize = MainGUI.finalsize;
	Random randompos = new Random();
	
	int CurrentXPos = randompos.nextInt(boardsize);
	int CurrentYPos = randompos.nextInt(boardsize);
	
	int r = randompos.nextInt(256);
	int g = randompos.nextInt(256);
	int b = randompos.nextInt(256);
	
	boolean eventvisit = false;
	boolean friendmeet = false;
	
	static void find(LadyBug bug[], Shop shop, boolean flag) {
		for (int i = 0; i < bug.length; i++) {
			if(shop.ShopXPos == bug[i].CurrentXPos && shop.ShopYPos == bug[i].CurrentYPos) {
				if(bug[i].eventvisit == true) {
					continue;
				}
				bug[i].eventvisit = true;
				if(flag) return;
				System.out.printf("BUG%d found the location of the bar!\n",i+1);
			}
		}
	}
	
	static void meet(LadyBug bug[],boolean flag) {
		for (int i = 0; i < bug.length; i++) {
			for (int j = 0; j < bug.length; j++) {
				if(i == j) {
					continue;
				}
				if(bug[i].eventvisit == true && bug[j].CurrentXPos == bug[i].CurrentXPos && bug[j].CurrentYPos == bug[i].CurrentYPos) {
					if(bug[j].eventvisit == true) { // 이미 술집을 알고있을 경우 메세지 생
						continue;
					}
					bug[j].eventvisit = bug[i].eventvisit; // 다른 벌레에게 장소 알려주
					if(flag) return;
					System.out.printf("BUG%d told BUG%d the location of the bar!\n",i+1,j+1);
				}
			}
		}
	}
	
	static boolean allKnown(LadyBug bug[]) {
		int count = 0;
		for (int i = 0; i < bug.length; i++) {
			if (bug[i].eventvisit == true) {
				count ++;
			}
		}
		if(bug.length == count) {
			return true;
		}
		return false;
	}
}
