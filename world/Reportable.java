package world;


import java.io.*;

/**
 * A class can implement the Reportable interface when it wants to be observed
 * by a Reporter class. Allows a class' state to be reported and produced as
 * output.
 * 
 * @author Dillon Kerr
 *
 */
public interface Reportable {
	
	/**
	 * Method to report state of the world.
	 */
	public void report();
	
	/**
	 * Method to set where output is written.
	 * @param printStream
	 */
	public void setDestination(PrintStream printStream);
	
	/**
	 * Method to set where output is written.
	 * @param printWriter
	 */
	public void setDestination(PrintWriter printWriter);
	
}
