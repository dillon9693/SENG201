package world;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JFileChooser;

import place.Room;
import thing.Thing;
import actor.Actor;
import actor.Person;

/**
 * Class which produces a textual report of a model world. Not automatically updated
 * when model state changes. Polls model to obtain current data and state.
 * @see World
 * @author Neville (updated by Matthias)
 * 
 */
public class Reporter implements Reportable 
{
	// Define where our output goes (console or file)
	private PrintWriter destination = null;
	private File file = null;
	
	private boolean makeDemo = true;
	private World world = new World(makeDemo);

	/**
	 * Method to report places by printing them to a destination (PrintWriter, e.g., file, console).	
	 * 
	 */
	private void reportPlaces() 
	{
		destination.println("Places:");
		Collection<Room> places = world.places();
	
		for (Room r : places) 
		{
			destination.println(r);

			// Since rooms may contain things we also need to report things for each room (if room is not empty).
			if (r.contents().size() > 0) 
			{
				destination.println("Contains:");
		
				for (Thing t : r.contents()) 
				{
					destination.println("   " + t.name() + " (" + t.description() + ")");
				}
			}
		}
	}

	/**
	 * Method to report items (or things) by printing them to a destination (PrintWriter, e.g., file, console).	
	 * 
	 */
	private void reportItems() 
	{
		destination.println("Items:");
		Collection<Thing> items  = world.items();
	
		for(Thing t : items) 
		{
			destination.println("   " + t.name() + " (" + t.description() + ")");
		}	
	}

	/**
	 * Method to report actors (persons) by printing them to a destination (PrintWriter, e.g., file, console).	
	 * 
	 */	
	private void reportActors() 
	{
		destination.println("Actors:");
		Collection<Actor> actors = world.actors();
	
		for (Actor p : actors) 
		{
			destination.println(p.name() + " is in " + p.location().label());
			
			// Since persons may be carrying items we also need to report items for each person (if person is carrying any items).
			if(p instanceof Person) {
				Person per = (Person)p;
				if (per.inventory().size() > 0) 
				{
					destination.println("Carrying:   ");
				
					for (Thing i : (ArrayList<Thing>) per.inventory()) 
					{
						destination.println("   " + i.name() + " (" + i.description() + ")");
					}
					destination.println();
				}
			}
		}
	}
	
	/**
	 * Constructor
	 * @param fileOutput Determines whether output is printed to file or to standard output stream (i.e., console).
	 * 	
	 */	
	public Reporter(boolean fileOutput) 
	{
		
		// If output is printed to a file.
				if (fileOutput) 
		{
			final JFileChooser fileChooser = new JFileChooser();
			switch (fileChooser.showSaveDialog(null)) 
			{
				case JFileChooser.CANCEL_OPTION:
					break;
				case JFileChooser.ERROR_OPTION:
					break;
				case JFileChooser.APPROVE_OPTION:
					file = fileChooser.getSelectedFile();
					try 
					{
						setDestination(new PrintWriter(file));
						System.out.println("Creating file: " + file.getPath());
					} catch (IOException e) 
					{
						e.printStackTrace();
						System.err.println("Error occurred in output file selection");
						System.exit(-1);
					}
					break;
				default:
					System.err.println("O noes!");
					System.exit(-42);
			}
		}
		
		// If output is NOT printed to a file.
		else
		{
			destination = new PrintWriter(System.out);
		}
	}

	/**
	 * Arrange reporting of world contents. 
	 * @param args Argument "-f" enables output file selection
	 * 
	 */
	public static void main(String[] args) 
	{
		
		// By default, output is not to a file.
		boolean fileOutput = false;
	
		switch((args.length))
		{
			case 0: // fileOutput already false, but legal
				break;
			case 1: fileOutput = args[0].equals("-f");
				break;
				default : System.err.println("Usage: [-f]");
		}
				
		Reporter me = new Reporter(fileOutput);
		me.report();
		System.out.println("Output printed to " + me.file);
	}

	@Override
	public void report() 
	{
		reportPlaces();
		reportItems();
		reportActors();
		
		// May not be necessary, but forces output stream to flush and close
		destination.flush();
		destination.close();
	}
	
	@Override
	public void setDestination(PrintStream printStream) 
	{
		destination = new PrintWriter(printStream);

	}
	
	@Override
	public void setDestination(PrintWriter printWriter) 
	{
		destination = printWriter;

	}
}