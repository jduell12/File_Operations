

import java.io.*;
import java.util.*;

public class FileOperations {

	StringTokenizer parseCommand;
	Scanner scan = null;
	PrintStream ps = null;

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
//			String fileName = parseCommand.nextToken();
//			System.out.println(fileName);
//			Need to figure out how to grab whole line from processCommandLine() in order to have the whole string in this class in order to create the file? 
	
			
	}

	public void printFile() {
		String command = parseCommand.nextToken();
		
	}

	void printUsage() {
		System.out.print("?\ncreateFile\nprintFile\nlastModified\nsize\nrename\nmkdir\ndelete\nlist\nquit\n");
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
			System.out.println("Missing a File name");
		} else {
			f = new File(fileName);
		}
		return f;
	}

	public boolean processCommandLine(String line) {
		StringTokenizer parseCommand = new StringTokenizer(line);
		boolean check = true;
		String command = "";
		
		if (line == null) {
			return false;
		} else {
			while (parseCommand.hasMoreTokens()) {
				command = parseCommand.nextToken();
				switch (command) {
				case "quit":
					check = false;
					break;
				case "createFile":
					createFile();
					check = true;
					break;
				case "printFile":
					printFile();
					check = true;
					break;
				case "lastModified":
					lastModified();
					check = true;
					break;
				case "size":
					size();
					check = true;
					break;
				case "rename":
					rename();
					check = true;
					break;
				case "mkdir":
					mkdir();
					check = true;
					break;
				case "delete":
					delete();
					check = true;
					break;
				case "list":
					list();
					check = true;
					break;
				case "?":
					printUsage();
					check = true;
					break;
				default:
					System.out.println("Command was invalid.");
					check = true;
					break;
				}
				return check;
			}
			return check;
		}
	}

	void processCommandFile(String commandFile) {
		try {
			FileInputStream fi = new FileInputStream(commandFile);
			scan = new Scanner(fi);
			while (scan.hasNext()) {
				String name = scan.nextLine();
				System.out.println("Processing: " + name);
				processCommandLine(name);
			}
		} catch (FileNotFoundException e) {
			System.out.println(commandFile + " file not found.");
		} finally {
			if (scan != null) {
				scan.close();
			}
		}
	}

	public static void main(String[] args) {
		FileOperations fo = new FileOperations();
		for (int i = 0; i < args.length; i++) {
			System.out.println("\n\n======================================== \n Starting command file = " + args[i]
					+ "\n========================================\n");
			fo.processCommandFile(args[i]);
		}
		System.out.println("Done with FileOperations");

	}

}
