# File_Operations

Create a package named file_operations and a class named
FileOperations which will receive one or more command line pathnames
to command files. Each command file will be opened and processed line by line. Each line can contain one of the following commands:
* ? - This command will print out the legal commands available
* createFile - The first string following "createFile" will be treated as a
filename, and the remaining strings will be written to the file separated
by new lines.
* printFile - The first string following "printFile" will be treated as a
filename that will be opened up and printed out to the screen.
* lastModified - The first string following "lastModified" will be treated as a
filename for which we will print out the date when this file was last
modified.
* size - The first string following "size" will be treated as a filename for
which we will print out the number of bytes it contains.
* rename - The first string following "rename" will be treated as the current filename and the second string will be treated as the new
filename we desire.
* mkdir - The first string following "mkdir" will be treated as the name of a
directory that should be created.
* delete - The first string following "delete" will be treated as the name of
a file that should be deleted.
* list - The first string following "list" will be treated as the name of a
directory for which we want a list of the files it contains.
* quit - exit program
* Anything else is a bad command