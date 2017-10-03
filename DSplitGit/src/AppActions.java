import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;

public class AppActions implements ActionListener {
	private FileAdmin admin;
	private boolean bbtnOutLocVisible = false;
	private boolean btFSizeVisible = false;
	
	public AppActions(FileAdmin admin) {
		this.admin = admin;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (admin == null) admin = new FileAdmin();
		String sCommandName = e.getActionCommand();
		if (e.getSource() instanceof JCheckBox) {
			if (sCommandName.equals("Select output location") )
			{
				bbtnOutLocVisible = !bbtnOutLocVisible;
				AppGui.btnOutLocSetVisible(bbtnOutLocVisible);
			}
			else if (sCommandName.equals("Set file size in Mb") )
			{
				btFSizeVisible = !btFSizeVisible;
				System.out.println(btFSizeVisible);
				AppGui.setJsMySpinnerVisible(btFSizeVisible);
			}
		}
		if (e.getSource() instanceof JButton) {
			
			if (sCommandName.equals("Select input File") )
			{
				try {
					admin.PickFile();
					AppGui.setTextLb1(admin.getFullFilePath());
					AppGui.setTextLb2(admin.getFilePath());
				} catch (FileNotFoundException ex) {
					ex.printStackTrace();
				}
			}
			else if (sCommandName.equals("Stop") )
			{
				admin.stopOperation(true);
			}
			else if (sCommandName.equals("Select output location") )
			{
				try {
					admin.PickFolder(admin.getFilePath());
					AppGui.setTextLb2(admin.getFolderPath());
				} catch (FileNotFoundException ex) {
					ex.printStackTrace();
				} catch (NullPointerException ex) {
					try {
						admin.PickFolder();
						AppGui.setTextLb2(admin.getFolderPath());
					} catch (FileNotFoundException ex2) {
						ex2.printStackTrace();
					}
					
				}
			}
			else if (sCommandName.equals("Start") )
			{
				admin.stopOperation(false);
				try {
					AppGui.SetProgressBarVal(0);
					new Thread(new Runnable() {
			            @Override
			            public void run() {
			            	admin.setBytesPerSplit(AppGui.getJsMySpinnerSize());
			            	
							try {
								admin.StartSplit(false);
							} catch (NullPointerException | IOException e) {
								MessageClass.ShowMsg(e.getMessage());
								e.printStackTrace();
							}
			            }
			        }).start();
					
				} catch (NullPointerException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

}
