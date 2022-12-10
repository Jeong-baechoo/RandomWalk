package RandomWalk;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

public class MainGUI {

	private JFrame frame;
	private JTextField boardsize;
	private JTextField bugcount;

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
		frame.setBounds(100, 100, 556, 378);
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
		noshop.setFont(new Font("굴림", Font.PLAIN, 15));
		noshop.setBounds(100, 92, 124, 42);
		frame.getContentPane().add(noshop);
		
		JButton shop = new JButton("술집 탐색하기");
		shop.setFont(new Font("굴림", Font.PLAIN, 15));
		shop.setBounds(306, 92, 139, 42);
		frame.getContentPane().add(shop);
		
		JLabel AnimationCount = new JLabel("애니메이션에서의 소요시간");
		AnimationCount.setFont(new Font("굴림", Font.PLAIN, 15));
		AnimationCount.setBounds(26, 194, 419, 36);
		frame.getContentPane().add(AnimationCount);
		
		JLabel AvgCount = new JLabel("5회 실행의 평균 수행시간은");
		AvgCount.setFont(new Font("굴림", Font.PLAIN, 15));
		AvgCount.setBounds(26, 253, 419, 36);
		frame.getContentPane().add(AvgCount);
	}
}
