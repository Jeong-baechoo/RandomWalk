package RandomWalk;

import java.util.Arrays;

public class RandomWalkDemo{
	static int size = MainGUI.finalsize;
	static int bugsize = MainGUI.finalbug;
	static int choosegame = MainGUI.choose;
	static int[][] field = new int[size][size];
	static int[][] fulledField = new int[size][size]; // 완성 
	
	
	public static void main(String[] args) {
		int loop = 0;
		int allcount = 0;
		boolean flag = false;
		
		while(loop<10) {
			for (int i = 0; i < field.length; i++) {
				for (int j = 0; j < field.length; j++) {
					field[i][j] = 0;
					fulledField[i][j] = 1;
				}
			}
			int count = 0;
		
			LadyBug bugs[] = new LadyBug[bugsize]; // 버그 객체 n개 생
	    	LadyBug tempBugs[] = new LadyBug[bugsize];
	    	for (int i = 0; i < tempBugs.length; i++) {
	    		bugs[i] = new LadyBug();
	    		tempBugs[i] = new LadyBug();
	    	}
	    	if (choosegame == 1){
	    		while(!Arrays.deepEquals(field, fulledField)) { 
	    			for (int i = 0; i < bugs.length; i++) {
	    				Movement.move(bugs[i], size);
	    			}
	    			count ++;
	    		}
			}
	    	else if (choosegame == 2) {
	    		Shop shop = new Shop();
	    		while(!LadyBug.allKnown(bugs)) { //LadyBug.find(bugs, Shop1)
	    			LadyBug.find(bugs, shop,true);
	    			LadyBug.meet(bugs, true);
	    			for (int i = 0; i < bugs.length; i++) {
	        			Movement.move(bugs[i], size);
	    			}
	    			count ++;
	    		}
	    	}

			allcount += count;
			++ loop;
		}
		allcount += RandomWalkAnimation.Animationcount;
		System.out.println("AVERAGE TIME: " + (allcount/loop));
	}
	
	static void initField()
	{
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field.length; j++) {
				field[i][j] = 0;
				fulledField[i][j] = 1;
			}
		}
	}
}
