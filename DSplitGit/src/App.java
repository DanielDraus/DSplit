import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.Window.Type;
import java.awt.Toolkit;

public class App {
	private FileAdmin admin;
	private boolean bNewButtonVisible = false;
	private boolean btFSizeVisible = false;
	private JFrame frame;
	private static JLabel label1 ;
	private static JLabel label2;
	public static JLabel jlbMsg;
	private static JSpinner jsMySpinner;
	public static JProgressBar progressBar;
	private static JButton btnOutLoc;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
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
	public App() {
		initialize();
		admin = new FileAdmin();
		
	}
	public static void SetProgressBar(int destIx)
	{
		App.progressBar.setValue(destIx);
        App.progressBar.setStringPainted(true);
        App.progressBar.repaint();  
    	
	}
	public static void InitProgressBar(int numSplits)
	{
		progressBar.setMinimum(0);
    	progressBar.setMaximum( numSplits);
    	
	}
	public static int getJsMySpinnerSize()
	{
		return Integer.parseInt(jsMySpinner.getValue().toString());
	}
	public static void setJsMySpinnerVisible(boolean bVisible)
	{
		jsMySpinner.setVisible(bVisible);
	}
	public static void SetProgressBarVal(int iVal)
	{
		progressBar.setValue(iVal);
	}
	public static void DisplayMsg(String sMsg)
	{
		jlbMsg.setText(sMsg);
	}
	public static void sCommandName(String fullFilePath) {
		label1.setText(fullFilePath);
	}
	public static void setTextLb1(String filePath) {
		label1.setText(filePath);
	}
	public static void setTextLb2(String filePath) {
		label2.setText(filePath);
	}
	public static void btnOutLocSetVisible(boolean bNewButtonVisible) {
		btnOutLoc.setVisible(bNewButtonVisible);
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		AppActions MyAppActions = new AppActions(admin);
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(App.class.getResource("/sun/print/resources/duplex.png")));
		frame.setResizable(false);
		frame.setType(Type.UTILITY);
		frame.setBounds(100, 100, 916, 403);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("DSplit by Daniel Draus");
		
		progressBar = new JProgressBar();
		
		JButton btnSelectFile = new JButton("Select input File");
		btnSelectFile.addActionListener(MyAppActions);
		
		btnOutLoc = new JButton("Select output location");
		btnOutLoc.setVisible(bNewButtonVisible);
		btnOutLoc.addActionListener(MyAppActions);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(MyAppActions);
		
		JButton btnStop = new JButton("Stop");
		btnStop.addActionListener(MyAppActions);
		
		label2 = new JLabel("");
		label2.setVerticalAlignment(SwingConstants.TOP);
		
		JLabel lblInputFile = new JLabel("Input file :");
		JLabel lblOutputFolder = new JLabel("Output folder  :");
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Select output location");
		chckbxNewCheckBox.addActionListener(MyAppActions);
		
		JCheckBox chckbxSetFileSize = new JCheckBox("Set file size in Mb");
		chckbxSetFileSize.addActionListener(MyAppActions);
		
		SpinnerModel sm = new SpinnerNumberModel(100, 1, 100, 8); //default value,lower bound,upper bound,increment by
		jsMySpinner = new JSpinner(sm);
		jsMySpinner.setVisible(btFSizeVisible);
		
		
		label1 = new JLabel();
		
		JSeparator separator = new JSeparator();
		JSeparator separator_1 = new JSeparator();
		JSeparator separator_2 = new JSeparator();
		
		jlbMsg = new JLabel("Ready");
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(53)
					.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, 806, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(39, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(43)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, 806, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(label2, GroupLayout.DEFAULT_SIZE, 901, Short.MAX_VALUE)
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(lblOutputFolder, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(chckbxSetFileSize)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(jsMySpinner, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE))
									.addComponent(lblInputFile)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(btnSelectFile)
										.addGap(7)
										.addComponent(btnOutLoc)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnStart)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnStop))
									.addComponent(chckbxNewCheckBox)
									.addComponent(label1, GroupLayout.PREFERRED_SIZE, 824, GroupLayout.PREFERRED_SIZE))
								.addGap(77)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(jlbMsg, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(Alignment.LEADING, groupLayout.createParallelGroup(Alignment.TRAILING)
									.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 806, GroupLayout.PREFERRED_SIZE)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(progressBar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 816, Short.MAX_VALUE)
										.addComponent(lblNewLabel, Alignment.LEADING))))
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSelectFile)
						.addComponent(btnOutLoc)
						.addComponent(btnStart)
						.addComponent(btnStop))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(chckbxNewCheckBox)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxSetFileSize)
						.addComponent(jsMySpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblInputFile)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label1)
					.addGap(6)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblOutputFolder)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(jlbMsg)
					.addPreferredGap(ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
					.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel)
					.addGap(42))
		);
		frame.getContentPane().setLayout(groupLayout);
	}

	

	

	
}
