package thing;

import java.util.Observable;


/**
 * Class which represents some item in a world.
 * @author Dillon Kerr
 *
 */
public class Thing extends Observable {
	
	private String description;
	private String name;
	
	/**
	 * Constructor (1 argument)
	 */
	public Thing(String name) {
		description = "";
		this.name = name;
	}
	
	/**
	 * Constructor (2 arguments)
	 * @param name - Name of thing
	 * @param description - Description of thing
	 */
	public Thing(String name, String description) {
		this.description = description;
		this.name = name;
	}
	
	/**
	 * Gets description of thing.
	 * @return Brief description of thing
	 */
	public String description() {
		return description;
	}
	
	/**
	 * Gets name of thing.
	 * @return Name of thing
	 */
	public String name() {
		return name;
	}
	
	/**
	 * Creates formatted string containing property details.
	 * 
	 */
	@Override
	public String toString() {
		String s = "Name: " + name + " (" + description + ")";
		return s;
	}
}
