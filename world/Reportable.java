package world;


import java.io.*;

public interface Reportable {
	
	public void report();
	
	public void setDestination(PrintStream printStream);
	
	public void setDestination(PrintWriter printWriter);
	
}
