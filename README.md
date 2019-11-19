# bujumbura-cli

This program communicates with by serial with "skranketellerne" & "skrankehjelperen".


## Requirements
 - [Java 6](https://www.oracle.com/java/technologies/javase-java-archive-javase6-downloads.html)
 - [Apache HttpClient 4.5.1](https://hc.apache.org/downloads.cgi)

## Setup

### Install Java 6 JDK & JRE  

This probably requires a manual extraction of the .bin-file.  
Change user to root: `su`.  
Make bin executable: `chmod +x [path-to-downloaded-bin]`.  
Execute the binary installation: `./[path-to-downloaded-bin]`.  
Make a new directory at JAVA_HOME `/usr/lib/jvm/` for Java 6.  
Move all the installed files that the binary created.  
Set both Java and Javac version to Java 6 (`update-alternatives --config /usr/bin/java java /path/to/java6 executable`).  
If you installed Java 6 in the `default-java`-directory, the path to the Java 6 executable would be `/usr/lib/jvm/default-java/bin/java`.

### Setup Java dependencies and compile
Make the directory `bin` at project root.  
Move rxtx-files to their respective directory as per the installation files in the folder.  
Unpack Apache HttpClient's lib-folder into a lib-folder in the root folder (i.e `~/bujumbura-cli`).  
Change directory to `bujumbura-cli/src/main/java`.  
Compile each file with the command `javac -cp ".:../../../lib/*" org/orakel[Filename].java -d ../../../bin`.  

## Run
Change directory to `bujumbura-cli/bin`.  
Run Bujumbura-cli with `java -cp .:../lib/\* org/orakel/KnappSys`.

### Known errors
"Could not find rxtxSerial in java.library.path" på Linux:  
Last ned librxtxSerial.so og plasser etter instruksjonene i rxtx-mappen.  
