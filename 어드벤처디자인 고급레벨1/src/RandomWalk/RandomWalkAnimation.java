package RandomWalk;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
public class RandomWalkAnimation extends JFrame {    
        static int M  = 10;					/*55까지로 제한 예정*/
        static JLabel [][] jLabel = new JLabel[M][M];
        static String bug = "◐";
        Font font = new Font("굴림", Font.PLAIN, 40);
        Font fontS = new Font("굴림", Font.PLAIN, 20);
        private LineBorder box = new LineBorder(Color.black, 1, true);
        static int Animationcount = 0;
        
    public RandomWalkAnimation() throws InterruptedException{
        JFrame jFrame = new JFrame("RandomWalk GUI");
        jFrame.pack();
        jFrame.setLayout(new GridLayout(M, M));
        if(M>6 & M<13) {
            jFrame.setSize(M*60, M*60);
        }
        else if (M>25) {
            jFrame.setSize(M*15, M*15);
        }
        else if (M<26 & M>12) {
            jFrame.setSize(M*30, M*30);
        }
        jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
        
        Color background = new Color(251,253,227);
        for(int i = 0; i < M; ++i) { 	// 그리드 JLabel 삽입
            for (int j = 0; j < M; ++j) {
                jLabel[i][j] = new JLabel( "" );
                if (M>12) {
                    jLabel[i][j].setFont(fontS);                    
                }
                else if (M<13) {
                    jLabel[i][j].setFont(font);
                }
                jLabel[i][j].setForeground(Color.RED);
                jLabel[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                jLabel[i][j].setSize(50, 50);
                jLabel[i][j].setOpaque(true);
                jLabel[i][j].setBorder(box);
                jLabel[i][j].setBackground(background);
                jFrame.add(jLabel[i][j]);
                
            }
        } 
        
       
        
        jFrame.setVisible(true);
        // 무당벌레 생성후 알고리즘 통해서 그리드에 나타나는 과정
        LadyBug bug1 = new LadyBug();
        LadyBug tempBug1 = new LadyBug(); // 이전 행적 나타내기 위한 버그 임시로 만들어
        
        RandomWalkDemo.initField(); // 배경판 초기
        Thread.sleep(1000);
        while(!Arrays.deepEquals(RandomWalkDemo.field, RandomWalkDemo.fulledField)) {
        	tempBug1.CurrentXPos = bug1.CurrentXPos;
        	tempBug1.CurrentYPos = bug1.CurrentYPos;
        	
        	jLabel[bug1.CurrentXPos][bug1.CurrentYPos].setText(bug);
        	jLabel[bug1.CurrentXPos][bug1.CurrentYPos].setBackground(new Color(0,255,0));        	
        	
        	Thread.sleep(5);
			Movement.move(bug1, M);
			
			jLabel[tempBug1.CurrentXPos][tempBug1.CurrentYPos].setText(" ");
			Animationcount ++;
		}
        System.out.println("소요시간: " + Animationcount);
        RandomWalkDemo.main(null);
    }

    
    static public void main(String[] arg) throws InterruptedException {
        new RandomWalkAnimation();
    }
}