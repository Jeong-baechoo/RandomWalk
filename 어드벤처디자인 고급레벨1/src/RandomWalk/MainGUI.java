package RandomWalk;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.TextArea;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainGUI {

	private static JFrame frame;
	private JTextField boardsize;
	private JTextField bugcount;
	static int choose = 0;
	static int finalsize = 0;
	static int finalbug = 0;
	static String output = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI window = new MainGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 570, 521);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("방의 크기 :");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel.setBounds(12, 10, 124, 36);
		frame.getContentPane().add(lblNewLabel);
		
		boardsize = new JTextField();
		boardsize.setBounds(118, 11, 124, 36);
		frame.getContentPane().add(boardsize);
		boardsize.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("벌레 수 :");
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(270, 10, 92, 36);
		frame.getContentPane().add(lblNewLabel_1);
		
		bugcount = new JTextField();
		bugcount.setBounds(351, 10, 111, 36);
		frame.getContentPane().add(bugcount);
		bugcount.setColumns(10);
		
		JButton noshop = new JButton("방 탐색하기");
		noshop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		noshop.addMouseListener(new MouseAdapter() {
			
			int size = 0;
			int bugs = 0;
            JOptionPane errormessage=new JOptionPane();
			public void mouseClicked(MouseEvent e) {
				try {
                    size = Integer.parseInt(boardsize.getText());                    
                }
                catch (NumberFormatException n) {
                    errormessage.showMessageDialog(null, "방크기에 잘못된 값을 입력하였습니다.");
                    return;
               }
                if(size<7||size>45) {
                errormessage.showMessageDialog(null, "방크기에 잘못된 값을 입력하였습니다.");
                return;
                }
                
                try {
                    bugs = Integer.parseInt(bugcount.getText());                    
                }
                catch (NumberFormatException n) {
                    errormessage.showMessageDialog(null, "벌레수에 잘못된 값을 입력하였습니다.");
                    return;
               }
                if(bugs<1||bugs>5) {
                errormessage.showMessageDialog(null, "벌레수에 잘못된 값을 입력하였습니다.");
                return;
                }
                RandomWalkAnimation.M = size;
                finalsize = size;
                RandomWalkAnimation.bugsizeWalk = bugs;
                finalbug = bugs;
                
                choose = 1;
               
               
                try {
                    new RandomWalkAnimation(); // 애니메이션 실
                } catch (InterruptedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
			}
		});
		noshop.setFont(new Font("굴림", Font.PLAIN, 15));
		noshop.setBounds(96, 135, 124, 42);
		frame.getContentPane().add(noshop);
		
		JButton shop = new JButton("술집 탐색하기");
		shop.addMouseListener(new MouseAdapter() {
			int size = 0;
			int bugs = 0;
            JOptionPane errormessage=new JOptionPane();
			public void mouseClicked(MouseEvent e) {
				try {
                    size = Integer.parseInt(boardsize.getText());                    
                }
                catch (NumberFormatException n) {
                    errormessage.showMessageDialog(null, "방크기에 잘못된 값을 입력하였습니다.");
                    return;
               }
                if(size<7||size>45) {
                errormessage.showMessageDialog(null, "방크기에 잘못된 값을 입력하였습니다.");
                return;
                }
                
                try {
                    bugs = Integer.parseInt(bugcount.getText());                    
                }
                catch (NumberFormatException n) {
                    errormessage.showMessageDialog(null, "벌레수에 잘못된 값을 입력하였습니다.");
                    return;
               }
                if(bugs<1||bugs>5) {
                errormessage.showMessageDialog(null, "벌레수에 잘못된 값을 입력하였습니다.");
                return;
                }
                RandomWalkFindShopAnimation.M = size;
                finalsize = size;
                RandomWalkFindShopAnimation.bugsizeShop = bugs;
                finalbug = bugs;
                choose = 2;
               
                try {
                    new RandomWalkFindShopAnimation(); // 애니메이션 실
                } catch (InterruptedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
			}
		});
		shop.setFont(new Font("굴림", Font.PLAIN, 15));
		shop.setBounds(302, 135, 139, 42);
		frame.getContentPane().add(shop);
		
		JSlider timeslider = new JSlider();
		timeslider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int sleep = timeslider.getValue();
				if (choose == 1) {
					RandomWalkAnimation.timeWalk = sleep;
				}
				else if (choose == 2) {
					RandomWalkFindShopAnimation.timeShop = sleep;
				}
			}
		});
		timeslider.setValue(200);
		timeslider.setPaintLabels(true);
		timeslider.setPaintTicks(true);
		timeslider.setMajorTickSpacing(50);
		timeslider.setMinorTickSpacing(10);
		timeslider.setMaximum(300);
		timeslider.setBounds(12, 207, 527, 71);
		frame.getContentPane().add(timeslider);
		
		JLabel lblNewLabel_2 = new JLabel("동작은 방의 크기가 7이상 45까지 동작합니다.");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(12, 68, 527, 29);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel(" 벌레의 수는 5개 이하로 입력해주세요.");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(86, 96, 358, 29);
		frame.getContentPane().add(lblNewLabel_3);
		
		
		
		JTextArea textArea = new JTextArea();
		PrintStream standardOut;
		
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 15));
		textArea.setBounds(12, 288, 532, 173);
		PrintStream printStream = new PrintStream(new CustomOutputStream(textArea));
		standardOut = System.out;
		System.setOut(printStream);
		System.setErr(printStream);
		frame.getContentPane().add(textArea);
	}
}

class CustomOutputStream extends OutputStream {
    private JTextArea textArea;
     
    public CustomOutputStream(JTextArea textArea) {
        this.textArea = textArea;
    }
     
    @Override
    public void write(int b) throws IOException {
        // redirects data to the text area
    	String proposer = String.valueOf((char)b);
        textArea.append(String.valueOf((char)b));
        // scrolls the text area to the end of data
        textArea.setCaretPosition(textArea.getDocument().getLength());
        }
}