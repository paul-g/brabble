import java.io.*;
import java.util.*;

class Main {

    public static List<String> readFile() throws Exception {
	BufferedReader br = new BufferedReader(new FileReader("text.in"));
	List<String> ss = new ArrayList<>();

	long usedMemory = Runtime.getRuntime().freeMemory();
	String line;
	while ( (line = br.readLine()) != null )
	    ss.add(line.intern());
	br.close();
	usedMemory -= Runtime.getRuntime().freeMemory();
	System.out.println("Used Memory " + usedMemory / (1000.0 * 1000.0) );
	return ss;
    }

    public static void main(String[] args) throws Exception {
	List<String> lines = readFile();

	System.out.println("Done! Going to sleep...");
	Thread.sleep(1000000);
    }
}
