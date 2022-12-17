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
        static int M = 56;					/*55까지로 제한 예정*/
        static int bugsizeWalk;
        static JLabel [][] jLabel = new JLabel[M][M];
        static String bugImage = "◐";
        Font font = new Font("굴림", Font.PLAIN, 40);
        Font fontS = new Font("굴림", Font.PLAIN, 20);
        private LineBorder box = new LineBorder(Color.black, 1, true);
        static int Animationcount = 0;
        static int timeWalk = 200;
        
    public RandomWalkAnimation() throws InterruptedException{
        JFrame jFrame = new JFrame("RandomWalk GUI");
        jFrame.pack();
        jFrame.setLayout(new GridLayout(M, M));
        if(M>6 & M<13) {						/*판의 크기에 따라 달라지는 grid 한칸의 크기*/
            jFrame.setSize(M*60, M*60);
        }
        else if (M>25) {
            jFrame.setSize(M*15, M*15);
            jFrame.setBounds(100, 100, 1200, 800);
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
                jLabel[i][j].setForeground(Color.BLACK);
                jLabel[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                jLabel[i][j].setSize(50, 50);
                jLabel[i][j].setOpaque(true);
                jLabel[i][j].setBorder(box);
                jLabel[i][j].setBackground(background);
                jFrame.add(jLabel[i][j]);
                
            }
        } 
        
        jFrame.setVisible(true);
        MyThread myThread = new MyThread();
        myThread.start();
        
    }
    
    static void animation () throws InterruptedException {
    	// 무당벌레 생성후 알고리즘 통해서 그리드에 나타나는 과정
    	LadyBug bugs[] = new LadyBug[bugsizeWalk]; // 버그 객체 n개 생
    	LadyBug tempBugs[] = new LadyBug[bugsizeWalk];
    	for (int i = 0; i < tempBugs.length; i++) {
    		bugs[i] = new LadyBug();
    		tempBugs[i] = new LadyBug();
    	} // 이전 행적 나타내기 위한 버그 임시로 만들어
    	
    	RandomWalkDemo.initField(); // 배경판 초기
    	Thread.sleep(1000);
    	while(!Arrays.deepEquals(RandomWalkDemo.field, RandomWalkDemo.fulledField)) {
    		for (int i = 0; i < bugs.length; i++) {
    			tempBugs[i].CurrentXPos = bugs[i].CurrentXPos;
    			tempBugs[i].CurrentYPos = bugs[i].CurrentYPos;
    			jLabel[bugs[i].CurrentXPos][bugs[i].CurrentYPos].setText(bugImage);
    			jLabel[bugs[i].CurrentXPos][bugs[i].CurrentYPos].setBackground(new Color(bugs[i].r,bugs[i].g,bugs[i].b));
    		}        	
    		if (M>14) {
    			Thread.sleep(timeWalk);
    		}
    		else if (M<15) {
    			Thread.sleep(timeWalk);
    		}
    		for (int i = 0; i < bugs.length; i++) {
    			Movement.move(bugs[i], M);
    			jLabel[tempBugs[i].CurrentXPos][tempBugs[i].CurrentYPos].setText(" ");
    		}
    		Animationcount ++;
    	}
    	String result = ("소요시간: " + Animationcount);
    	System.out.println(result);
    	MainGUI.output = result;
    	RandomWalkDemo.main(null); 	
    }

    
    static public void main(String[] arg) throws InterruptedException {
        new RandomWalkAnimation();
    }
}

class MyThread extends Thread{
    @Override
    public void run() {
        try {
            RandomWalkAnimation.animation();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        super.run();
    }
}