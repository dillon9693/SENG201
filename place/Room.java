package place;


import java.util.*;

import thing.Thing;
/**
 * Class to model a room in a world. People and items may be located in a room.
 * @author Dillon Kerr
 *
 */
public class Room extends Observable {
	
	private ArrayList<Thing> items;
	private double depth, width;
	private String description;
	private String label;
	private int level;
	
	/**
	 * Constructor
	 * @param label - Label for room.
	 */
	public Room(String label) {
		items = new ArrayList<Thing>();
		depth = 5.0;
		width = 5.0;
		description = "";
		this.label = label;
		level = 1;
	}
	
	/**
	 * Constructor
	 * @param label - Label for room.
	 * @param level - Level for room; can be negative, zero is ground floor.
	 */
	public Room(String label, int level) {
		items = new ArrayList<Thing>();
		depth = 5.0;
		width = 5.0;
		description = "";
		this.label = label;
		this.level = level;
	}
	
	/**
	 * Constructor
	 * @param label - Label for room.
	 * @param level - Level for room; zero is ground flood
	 * @param description - Brief description of room
	 */
	public Room(String label, int level, String description) {
		items = new ArrayList<Thing>();
		depth = 5.0;
		width = 5.0;
		this.description = description;
		this.label = label;
		this.level = level;
	}
	
	/**
	 * Contents of room.
	 * @return - Collection containing contents of room.
	 */
	public Collection<Thing> contents() {
		return items;
	}
	
	/**
	 * List content of room.
	 * @return - String containing comma-delimited list of content item names.
	 */
	public String contentsList() {
		String s = "";
		for(Thing t : items) {
			s += ("" + t.name() + ",");
		}
		return s;
	}
	
	/**
	 * Adds a thing to the room's contents.
	 * @param t - Thing to add to room.
	 */
	public void add(Thing t) {
		items.add(t);
		update(t);
	}
	
	/**
	 * Removes a thing from the room's contents.
	 * @param t - Thing to remove from room.
	 */
	public void remove(Thing t) {
		items.remove(t);
		update(t);
	}
	
	/**
	 * Get brief description of room.
	 * @return - Room description.
	 */
	public String description() {
		return description;
	}
	
	/**
	 * Set the description for a room.
	 * @param description - New or update description of room.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Get label as it appears on the door of the room.
	 * @return - Room label.
	 */
	public String label() {
		return label;
	}
	
	/**
	 * Set the label of the room.
	 * @param label - Label of room.
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	
	/**
	 * Get level the room is on.
	 * @return - Room level; can be negative, zero is ground floor.
	 */
	public int level() {
		return level;
	}
	
	/**
	 * Set level of room.
	 * @param level - Level of room.
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	
	/**
	 * Get depth of the room.
	 * @return - Depth of room
	 */
	public double depth() {
		return depth;
	}
	
	/**
	 * Get width of room.
	 * @return - Width of room
	 */
	public double width() {
		return width;
	}
	
	/**
	 * Set size / dimensions of room in metres.
	 * @param width - Room width
	 * @param depth - Room depth
	 */
	public void setSize(double width, double depth) {
		this.width = width;
		this.depth = depth;
	}
	
	/**
	 * Sends update to observer
	 * @param o - Object that is updated
	 */
	private void update(Object o) {
		setChanged();
		notifyObservers(o);
	}
	
	/**
	 * Room-specific version of toString()
	 */
	public String toString() {
		String s = "[Room: number = "+label+", level = "+level+", "+description+"]";
		return s;
	}
}

