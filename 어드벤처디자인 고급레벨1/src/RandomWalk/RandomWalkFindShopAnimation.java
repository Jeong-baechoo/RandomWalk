package RandomWalk;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
public class RandomWalkFindShopAnimation extends JFrame {    
        static int M = 56;					/*55까지로 제한 예정*/
        static int bugsizeShop;
        static JLabel [][] jLabel = new JLabel[M][M];
        static String bugImage = "◐";
        static String shopImage = "★";
        Font font = new Font("굴림", Font.PLAIN, 40);
        Font fontS = new Font("굴림", Font.PLAIN, 20);
        private LineBorder box = new LineBorder(Color.black, 1, true);
        static int Animationcount = 0;
        static int timeShop = 200;
        
    public RandomWalkFindShopAnimation() throws InterruptedException{
        JFrame jFrame = new JFrame("RandomWalk Shop GUI");
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
        MyThread2 myThread = new MyThread2();
        myThread.start();
    }
    
    static void shopanimation() throws InterruptedException {
    	// 무당벌레 생성후 알고리즘 통해서 그리드에 나타나는 과정
    	LadyBug bugs[] = new LadyBug[bugsizeShop]; // 버그 객체 n개 생
    	LadyBug tempBugs[] = new LadyBug[bugsizeShop];
    	for (int i = 0; i < tempBugs.length; i++) {
    		bugs[i] = new LadyBug();
    		tempBugs[i] = new LadyBug();
    	}
    	
    	
    	Shop shop = new Shop();
    	RandomWalkDemo.initField(); // 배경판 초기
    	Thread.sleep(1000);
    	while(!LadyBug.allKnown(bugs)) { //LadyBug.find(bugs, Shop1)
    		LadyBug.find(bugs, shop,false);
    		LadyBug.meet(bugs,false);
    		
    		for (int i = 0; i < bugs.length; i++) {
    			tempBugs[i].CurrentXPos = bugs[i].CurrentXPos;
    			tempBugs[i].CurrentYPos = bugs[i].CurrentYPos;
    			jLabel[bugs[i].CurrentXPos][bugs[i].CurrentYPos].setText(bugImage);
    			jLabel[bugs[i].CurrentXPos][bugs[i].CurrentYPos].setBackground(new Color(bugs[i].r,bugs[i].g,bugs[i].b));
    		}        	
    		
    		jLabel[shop.ShopXPos][shop.ShopYPos].setText(shopImage);
    		jLabel[shop.ShopXPos][shop.ShopYPos].setForeground(Color.RED);
    		if (M>14) {
    			Thread.sleep(timeShop);        		
    		}
    		else if (M<15) {
    			Thread.sleep(timeShop);
    		}
    		for (int i = 0; i < bugs.length; i++) {
    			Movement.move(bugs[i], M);
    			jLabel[tempBugs[i].CurrentXPos][tempBugs[i].CurrentYPos].setText(" ");
    		}
    		Animationcount ++;
    		
    	}
    	String result = ("TIME: " + Animationcount);
    	System.out.println(result);
    	MainGUI.output = result;
    	RandomWalkDemo.main(null);
    }

    
    static public void main(String[] arg) throws InterruptedException {
        new RandomWalkFindShopAnimation();
    }
}

class MyThread2 extends Thread{
    @Override
    public void run() {
        try {
            RandomWalkFindShopAnimation.shopanimation();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        super.run();
    }
}