

import java.io.*;
import java.util.*;

public class FileOperations {

	StringTokenizer parseCommand;
	Scanner scan = null;
	PrintStream ps = null;

	// the next 9 classes will be the command words needed in the file to use that class
	
	//deletes first string following the command from a file
	public void delete(String line) {
		StringTokenizer parseCommand = new StringTokenizer(line);
		File f = new File (parseCommand.nextToken());
		
		if (f.exists()) {
			System.out.println("Deleting " + f.getAbsolutePath());
			f.delete();
			if (f.exists() == false) {
				System.out.println("Successful delete \n");
			} else {
				System.out.println("Unsuccessful delete \n");
			}
		} else {
			System.out.println("A file with that name has not been created.\n");
		}
	}

	// first string after command is treated as the file that should be renamed and the second string is the new name for the file
	public void rename(String line) {
		StringTokenizer parseCommand = new StringTokenizer(line);
		File f = new File (parseCommand.nextToken());
		
		if (f.exists()) {
			String name = parseCommand.nextToken();
			File g = new File(name);
			f.renameTo(g);
			System.out.println("Renaming " + f.getAbsolutePath() + " to " + g.getAbsolutePath());
			System.out.println("Successful rename \n");
		} else {
			System.out.println("A file with that name has not been created.\n");
		}
	}

	//first string after command will be treated as directory for which we want a list of the files it contains
	public void list(String line) {
		StringTokenizer parseCommand = new StringTokenizer(line);
		File f = new File (parseCommand.nextToken());
		
		if (f.exists()) {
			System.out.println("Listing files for " + f.getAbsolutePath() + "\n");
			String [] paths = f.list();
			for (String path: paths) {
				System.out.println(path);
			}
			System.out.println("\n");
		} else {
			System.out.println("A file with that name has not been created.\n");
		}
	}

	//first string after command is the filename we wish to find the size of and prints out the number of bytes the file contains
	public void size(String line) {
		StringTokenizer parseCommand = new StringTokenizer(line);
		File f = new File (parseCommand.nextToken());
		
		if (f.exists()) {
			try {
				RandomAccessFile ranFile = new RandomAccessFile(f, "rw");
				long length = ranFile.length();
				System.out.println("Size for " + f.getAbsolutePath() + " is = " + length + "\n");
				ranFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("A file with that name has not been created. \n");
		}
	}

	//first string after command will be treated as the filename for which will be printed out the date when the file was last modified 
	public void lastModified(String line) {
		StringTokenizer parseCommand = new StringTokenizer(line);
		File f = new File(parseCommand.nextToken());
		
		if (f.exists()) {
			System.out.println("Last modified for " + f.getAbsolutePath());
			long time = f.lastModified();
			Date d = new Date(time);
			System.out.println(d + "\n");
		} else {
			System.out.println("A file with that name has not been created. \n");
		}
		
	}

	//first string following command will be treated as name of directory that should be created
	public void mkdir(String line) {
		StringTokenizer parseCommand = new StringTokenizer(line);
		String name = parseCommand.nextToken();
		
		File f = new File(name);
		f.mkdirs();
		
		System.out.println("Successful creation of directory: " + f.getAbsolutePath() + "\n");
	}

	//first string following command will be treated as filename for a new file. Any remaining strings will be written to the file
	public void createFile(String line) {
		StringTokenizer parseCommand = new StringTokenizer(line, " ");
		String fileName = parseCommand.nextToken();
		File f = new File (fileName);
		
		try {
			RandomAccessFile writer = new RandomAccessFile(f, "rw");
			while(parseCommand.hasMoreTokens()) {
				writer.writeUTF(parseCommand.nextToken() + "\n");
			}
			System.out.println("Created file for " + f.getAbsolutePath() + "\n");
			writer.close();
		} catch  (IOException e) {
			e.printStackTrace();
		}
		
	}

	//first string following command will be treated as the filename for which we will open and print to the screen
	public void printFile(String line) {
		StringTokenizer parseCommand = new StringTokenizer(line, " ");
		File name = new File (parseCommand.nextToken());
		
		if (name.exists()) {
			try {
				RandomAccessFile reader = new RandomAccessFile (name, "rw");
				String text = "";
				while ((text = reader.readLine()) != null) {
					System.out.println(text);
				}
				System.out.println("Printed file for " + name.getAbsolutePath() + "\n");
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		} else {
			System.out.println("A file with that name has not been created.\n");
		}
	}

	//command is "?"; prints out legal commands available 
	void printUsage() {
		System.out.print("?\ncreateFile\nprintFile\nlastModified\nsize\nrename\nmkdir\ndelete\nlist\nquit\n\n");
	}

	//checks to see if line is null; if there is something in the line it will take the first word and use it as the command word which will call the specific class; continues until there is no more lines in the file 
	public boolean processCommandLine(String line) {
		StringTokenizer parseCommand = new StringTokenizer(line, " ");
		boolean check = true;
		String command = "";
		String fullLine = "";
		
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
					fullLine = parseCommand.nextToken("\n");
					createFile(fullLine);
					check = true;
					break;
				case "printFile":
					fullLine = parseCommand.nextToken("\n");
					printFile(fullLine);
					check = true;
					break;
				case "lastModified":
					fullLine = parseCommand.nextToken("\n");
					lastModified(fullLine);
					check = true;
					break;
				case "size":
					fullLine = parseCommand.nextToken("\n");
					size(fullLine);
					check = true;
					break;
				case "rename":
					fullLine = parseCommand.nextToken("\n");
					rename(fullLine);
					check = true;
					break;
				case "mkdir":
					fullLine = parseCommand.nextToken("\n");
					mkdir(fullLine);
					check = true;
					break;
				case "delete":
					fullLine = parseCommand.nextToken("\n");
					delete(fullLine);
					check = true;
					break;
				case "list":
					fullLine = parseCommand.nextToken("\n");
					list(fullLine);
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

	//open file commandFile and calls processCommandLine for each line read and continues to read from the file until processCommandLine returns false
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
