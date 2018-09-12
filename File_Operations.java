

import java.io.File;
import java.util.*;

public class FileOperations {
	
	StringTokenizer parseCommand;
	
	public void delete() {
		
	}
	
	public void rename() {
		
	}
	
	public void list() {
		
	}
	
	public void size() {
		
	}
	
	public void lastModified() {
		
	}
	
	public void mkdir() {
		
	}
	
	public void createFile() {
		
	}
	
	public void printFile() {
		
	}
	
	void printUsage() {
		
	}
	
	private String getNextToken() {
		if (parseCommand.hasMoreTokens()) {
			return parseCommand.nextToken();
		} else {
			return null;
		}
	}
	
	private File getFile() {
		File f = null;
		String fileName = getNextToken();
		if (fileName == null) {
			System.out.println("Missing a File name"); } else {
				f = new File(fileName);	
			}
		return f;
		}
	
	public boolean processCommandLine(String line) {
		return true;
	}
	
	void processCommandFile(String commandFile) {
		
	}
	
	public static void main(String[] args) {
		FileOperations fo= new FileOperations();
        for (int i=0; i < args.length; i++)
        {
System.out.println("\n\n============ Processing " + args[i] +" =======================\n");
            fo.processCommandFile(args[i]);
        }
System.out.println("Done with FileOperations");

	}

}
