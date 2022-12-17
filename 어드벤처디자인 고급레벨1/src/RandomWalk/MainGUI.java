package RandomWalk;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainGUI {

	private JFrame frame;
	private JTextField boardsize;
	private JTextField bugcount;
	int choose = 0;
	static int event = 0;
	static String print;

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
		frame.setBounds(100, 100, 571, 493);
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
                if(size<4||size>56) {
                errormessage.showMessageDialog(null, "방크기에 잘못된 값을 입력하였습니다.");
                return;
                }
                RandomWalkAnimation.M = size;
                
                try {
                    bugs = Integer.parseInt(bugcount.getText());                    
                }
                catch (NumberFormatException n) {
                    errormessage.showMessageDialog(null, "벌레수에 잘못된 값을 입력하였습니다.");
                    return;
               }
                if(bugs<1||bugs>3) {
                errormessage.showMessageDialog(null, "벌레수에 잘못된 값을 입력하였습니다.");
                return;
                }
                RandomWalkAnimation.bugsizeWalk = bugs;
                
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
                if(size<4||size>56) {
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
                if(bugs<1||bugs>3) {
                errormessage.showMessageDialog(null, "벌레수에 잘못된 값을 입력하였습니다.");
                return;
                }
                RandomWalkFindShopAnimation.M = size;
                RandomWalkFindShopAnimation.bugsizeShop = bugs;
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
		timeslider.setValue(250);
		timeslider.setPaintLabels(true);
		timeslider.setPaintTicks(true);
		timeslider.setMajorTickSpacing(100);
		timeslider.setMinorTickSpacing(50);
		timeslider.setMaximum(500);
		timeslider.setBounds(12, 207, 527, 71);
		frame.getContentPane().add(timeslider);
		
		JLabel lblNewLabel_2 = new JLabel("동작은 방의 크기가 4이상 55까지 동작합니다.");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(12, 68, 527, 29);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel(" 벌레의 수는 3개 이하로 입력해주세요.");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(86, 96, 358, 29);
		frame.getContentPane().add(lblNewLabel_3);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 15));
		textArea.setBounds(22, 288, 517, 126);
		frame.getContentPane().add(textArea);
		
		
	}
}
