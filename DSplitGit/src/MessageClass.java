import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MessageClass {
	private static int iWidth ;
	private static int iHeight ;
	private static String sTitle = "DSplit By Daniel Draus";
	private static String sLabText= "Welcome in DSplit";
	private static StringBuilder sb;
	private static JLabel jlbempty ;
	private static boolean isSwingWindowRun;
	public MessageClass(int iWidthc, int iHeightc) {
		iHeight = iHeightc;
		iWidth = iWidthc;
		isSwingWindowRun = true;
	}
	public MessageClass(int iWidthc, int iHeightc, String stitle2, String slabeltext) {
		iHeight = iHeightc;
		iWidth = iWidthc;
		sTitle = stitle2;
		sLabText = slabeltext;
		isSwingWindowRun = false;
	}
	public static void displayPanel() {
		JFrame frame = new JFrame(sTitle);
		frame.setLayout(null);
		frame.setPreferredSize(new Dimension(iWidth, iHeight));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((dim.width - iWidth) /2, (dim.height-iHeight)/2);
		
		// Add a window listner for close button
		frame.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		// This is an empty content area in the frame
		System.out.println(repeat(sLabText, 3));
		sb = new StringBuilder(64);
        sb.append("<html>Please choose a file to split.");
		jlbempty = new JLabel(sb.toString());
		//jlbempty.setPreferredSize(new Dimension(580, 200));
		jlbempty.setBounds(10,50,560,123);
		Font font = new Font("Verdana", 3, 12);
		jlbempty.setFont(font);
		/*JButton bJButton = new JButton("Select file");
		//bJButton.setPreferredSize(new Dimension(580, 100));
		bJButton.setBounds(10,10,560,30);
		
		bJButton.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				MessageClass MyMessageClass = new MessageClass(MessageClass.iWidth,MessageClass.iHeight);
				MyMessageClass.AddMsg("TODO Auto-generated method stub");
				
			}
			
		});
		frame.getContentPane().add(bJButton);//, BorderLayout.NORTH);
		*/
	
		frame.getContentPane().add(jlbempty);//, BorderLayout.NORTH);
		frame.pack();
		frame.setVisible(true);
	}
	public static String repeat(String s, int n) {
	    if(s == null) {
	        return null;
	    }
	    final StringBuilder sb = new StringBuilder(s.length() * n);
	    for(int i = 0; i < n; i++) {
	        sb.append(s);
	    }
	    return sb.toString();
	}
	public void AddMsg(String sMsg)
	{
		if (sb == null) 
		{
			sb = new StringBuilder(64);
			sb.append("<Html>");
		}
        
		if (sb.length() > 220) 
		{
			sb.delete(0,sb.lastIndexOf("</p>") + 5 );
			sb.append("<Html>");
		}
		sb.append("<p>" + sMsg + "</p>");
		System.out.println(sb.length() + "- " + sb.toString());;
		if (!isSwingWindowRun) 
		{
			jlbempty.setText(sb.toString());
		} else {
			App.jlbMsg.setText(sb.toString());
		}
			
		
	}
	public static void ShowMsg(String sMsg) {
		if (!isSwingWindowRun) 
		{
			jlbempty.setText(sMsg);
		} else {
			App.jlbMsg.setText(sMsg);
		}
		
	}
}