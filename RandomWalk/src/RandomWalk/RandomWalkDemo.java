package RandomWalk;

import java.util.Arrays;

public class RandomWalkDemo{
	static int[][] field = new int[10][10];
	static int[][] fulledField = new int[10][10]; // 완성 
	public static void main(String[] args) {
		
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field.length; j++) {
				field[i][j] = 0;
				fulledField[i][j] = 1;
			}
		}
		int count = 0;
		
		LadyBug bug1 = new LadyBug();
		Movement mv = new Movement();
		while(!Arrays.deepEquals(field, fulledField)) { 
			Movement.move(bug1);
			count ++;
		}
		
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field.length; j++) {
				System.out.print(field[i][j]);
			}
			System.out.println();
		}
		
		System.out.println("소요시간: " + count);
	}
}