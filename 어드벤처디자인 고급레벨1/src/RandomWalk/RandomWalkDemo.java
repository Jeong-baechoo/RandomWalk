package RandomWalk;

import java.util.Arrays;

public class RandomWalkDemo{
	static int size = RandomWalkAnimation.M;
	static int[][] field = new int[size][size];
	static int[][] fulledField = new int[size][size]; // 완성 
	
	public static void main(String[] args) {
		int loop = 0;
		int allcount = 0;
		
		while(loop<5) {
			for (int i = 0; i < field.length; i++) {
				for (int j = 0; j < field.length; j++) {
					field[i][j] = 0;
					fulledField[i][j] = 1;
				}
			}
			int count = 0;
		
			LadyBug bug1 = new LadyBug();
			while(!Arrays.deepEquals(field, fulledField)) { 
				Movement.move(bug1,size);
				count ++;
			}
		
//			for (int i = 0; i < field.length; i++) {
//				for (int j = 0; j < field.length; j++) {
//					System.out.print(field[i][j]);
//				}
//				System.out.println();
//			}
//			System.out.println("소요시간: " + count + "\n");
			allcount += count;
			++ loop;
		}
		allcount += RandomWalkAnimation.Animationcount;
		System.out.println("평균 소요시간: " + (allcount/loop));
		MainGUI.event = 1;
		MainGUI.print = ("평균 소요시간: " + (allcount/loop));
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
