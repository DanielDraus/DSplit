import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

/**
 * 
 */

/**
 * @author draus
 *
 */
public  class FileAdmin {
	 private static File selectedFile = null;
	 private static File selectedFolder = null;
	 private static boolean bstopOperation = false;
	 private static JDialog JDialoga = new JDialog ();
	 private  MessageClass MyMessageClass;
	 private long bytesPerSplit = 104857600L ;//100Mb 103809024L ; //99Mb ;//sourceSize/numSplits ;
     
	 public FileAdmin( ) {
		
	 }
	 public FileAdmin(MessageClass MyMessageClass) {
		 this.setMyMessageClass(MyMessageClass);
	 
	 }
	 /**
     * Read amount of bytes from input and write to output
     * <p>
     * This method read and write amount of bytes from RandomAccessFile to BufferedOutputStream
     * @param  raf input RandomAccessFile
     * @param  bw output BufferedOutputStream
     * @param  numBytes number of bytes for read and write
     * @throws IOException 
     * @see    RandomAccessFile
     * @see    BufferedOutputStream
     */
	public   void readWrite(RandomAccessFile raf, BufferedOutputStream bw, long numBytes) throws IOException
	{ 
		byte[] buf = new byte[(int) numBytes];
        int val = raf.read(buf);
        if(val != -1) {
            bw.write(buf);
        }
	}
	
	/**
     * Set static variable selectedFile 
     * <p>
     * This method create JFileChooser object with user.home path 
     * and set static variable selectedFile
     * @throws FileNotFoundException 
     * @see    String
     */
	public void setBytesPerSplit(int iBytesPerSplit)
	{ 
		bytesPerSplit = iBytesPerSplit * 1048576;
    	
	}
    /**
     * Set static variable selectedFile 
     * <p>
     * This method create JFileChooser object with user.home path 
     * and set static variable selectedFile
     * @throws FileNotFoundException 
     * @see    String
     */
	public void PickFile() throws FileNotFoundException
	{ 
		JFileChooser fileChooser = new JFileChooser();
    	fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
    	int result = fileChooser.showOpenDialog(JDialoga);
    	if (result == JFileChooser.APPROVE_OPTION) {
    	    selectedFile = fileChooser.getSelectedFile();
    	}
    	
	}
	public void stopOperation(boolean bStop)
	{
		FileAdmin.bstopOperation = bStop;	
	}
	
	/**
     * Set static variable selectedFile 
     * <p>
     * This method create JFileChooser object with user.home path 
     * and set static variable selectedFile
     * @throws FileNotFoundException 
     * @see    String
     */
	public void PickFolder() throws FileNotFoundException
	{ 
		JFileChooser fileChooser = new JFileChooser();
    	fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
    	fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
    	int result = fileChooser.showOpenDialog(JDialoga);
    	if (result == JFileChooser.APPROVE_OPTION) {
    	    selectedFolder = fileChooser.getSelectedFile();
    	}
    	
	}
	public void PickFolder(String sPath) throws FileNotFoundException
	{ 
		JFileChooser fileChooser = new JFileChooser();
    	fileChooser.setCurrentDirectory(new File(sPath));
    	fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
    	int result = fileChooser.showOpenDialog(JDialoga);
    	if (result == JFileChooser.APPROVE_OPTION) {
    	    selectedFolder = fileChooser.getSelectedFile();
    	}
    	
	}
    /**
     * Return String Object 
     * <p>
     * This method create String object with selectedFile path 
     * @return      the extension of file at the specified selectedFile
     * @see         String
     */
	private  String getExtension()
	{
		String ext = null;
        String s = selectedFile.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1);
        }
        return ext;
	}
	/**
     * Return String Object 
     * <p>
     * This method create String object with selectedFile path 
     * @return      the file name without extension at the specified selectedFile
     * @see         String
     */
	private String getFileName() {
        String ext = null;
        String s = selectedFile.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(0, i);
        }
        return ext;
    }
	/**
     * Return String Object 
     * <p>
     * This method create String object with selectedFile path 
     * @return      the file path at the specified selectedFile
     * @see         String
     */
	public String getFullFilePath() {
   	 	return selectedFile.getPath();
	}
    /**
     * Return String Object 
     * <p>
     * This method create String object with selectedFile path 
     * @return      the file path at the specified selectedFile
     * @see         String
     */
	public String getFilePath() {
   	 	String ext = null;
        String s = selectedFile.getPath();
        int i = s.lastIndexOf('\\');
        if ( i < 3) 
        {
       	 i = s.lastIndexOf('/');
             
        }
        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(0, i);
        }
        return ext;
    }
	/**
     * Return String Object 
     * <p>
     * This method create String object with selectedFolder path 
     * @return      the file path at the specified selectedFolder
     * @see         String
     */
	public String getFolderPath() {
		if (selectedFolder == null) return getFilePath();
   	 	return  selectedFolder.toString();
        
    }
	public void StartSplit(boolean bPickFile) throws IOException,NullPointerException
	{
		if (bPickFile)
		{
			PickFile();
			selectedFolder = selectedFile;
		} else {
			this.MyMessageClass = new MessageClass(600,400);
		}
		if (selectedFile == null )
		{
			MessageClass.ShowMsg("Please select file");
			return;
		}
		String sFileToSplit = selectedFile.getAbsolutePath();
		RandomAccessFile raf = new RandomAccessFile(sFileToSplit, "r");
		long numSplits = 10; //from user input, extract it from args
        long sourceSize = raf.length();
        numSplits = (sourceSize / bytesPerSplit) + 1;
        AppGui.InitProgressBar((int) numSplits);
    	this.MyMessageClass.AddMsg("Split " + sFileToSplit + " to " + numSplits + " files"); 
        
        int maxReadBufferSize = 1048576; // 1 Mb 1024 * 8 ;//8kB //
        AppGui.SetProgressBar(0);
        try {
			java.lang.Thread.sleep(1000);
		} catch (InterruptedException e) {
			MyMessageClass.AddMsg(e.getMessage());
			System.out.println(e.getMessage());
		}
        
        for(int destIx=1; destIx <= numSplits; destIx++) {
        	if (FileAdmin.bstopOperation)  return;
        	String sNewSplitFile = getFolderPath() + "/" + getFileName() +  "_"+destIx + "." + getExtension();
        	AppGui.SetProgressBar(destIx); 
        	BufferedOutputStream bw = new BufferedOutputStream(new FileOutputStream(sNewSplitFile));
            if(bytesPerSplit > maxReadBufferSize) {
                long numReads = bytesPerSplit/maxReadBufferSize;
                long numRemainingRead = bytesPerSplit % maxReadBufferSize;
                for(int i=0; i<numReads; i++) {
                    readWrite(raf, bw, maxReadBufferSize); 
                }
                if(numRemainingRead > 0) {
                    readWrite(raf, bw, numRemainingRead);
                }
            }else {
                readWrite(raf, bw, bytesPerSplit);
            }
            bw.close();
            this.MyMessageClass.AddMsg("File " + sNewSplitFile + " created");
        }
            raf.close();
	}
	
	public MessageClass getMyMessageClass() {
		return MyMessageClass;
	}
	
	public void setMyMessageClass(MessageClass myMessageClass) {
		MyMessageClass = myMessageClass;
	}

}
