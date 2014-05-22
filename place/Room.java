package place;


import java.util.*;

import thing.Thing;
/**
 * Class to model a room in a world. People and items may be located in a room.
 * @author Dillon Kerr
 *
 */
public class Room extends Observable {
	
	private Collection<Thing> items;
	private double depth;
	private double width;
	private String description;
	private String label;
	private int level;
	
	/**
	 * Constructor
	 * @param label - Label for room.
	 */
	public Room(String label) {
		items = new ArrayList<Thing>();
		depth = 1.0;
		width = 1.0;
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
		depth = 0.0;
		width = 0.0;
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
		depth = 0.0;
		width = 0.0;
		this.description = description;
		this.label = label;
		this.level = level;
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
	 * Contents of room.
	 * @return Collection containing contents of room.
	 */
	public Collection<Thing> contents() {
		return items;
	}
	
	/**
	 * List content of room.
	 * @return String containing comma-delimited list of content item names.
	 */
	public String contentsList() {
		String s = "";
		for(Thing t : items) {
			s += ("" + t.name() + ",");
		}
		return s;
	}
	
	
	/**
	 * Get room depth in metres.
	 * @return Depth of rectangular room.
	 */
	public double depth() {
		return depth;
	}
	
	/**
	 * Get brief description of room.
	 * @return Room description.
	 */
	public String description() {
		return description;
	}
	
	/**
	 * Get label as it appears on the door of the room.
	 * @return Room label.
	 */
	public String label() {
		return label;
	}
	
	/**
	 * Get level the room is on.
	 * @return Room level; can be negative, zero is ground floor.
	 */
	public int level() {
		return level;
	}
	
	/**
	 * Set the description for a room.
	 * @param description - New or update description of room.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Set the label of the room.
	 * @param label - Label of room.
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	
	/**
	 * Set level of room.
	 * @param level - Level of room.
	 */
	public void setLevel(int level) {
		this.level = level;
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
	 * Formatted string giving state detail.
	 */
	@Override
	public String toString() {
		String s = "[Room: number = "+label+", level = "+level+", "+description+"]";
		return s;
	}
	
	/**
	 * Get room width in metres.
	 * @return Width of rectangular room.
	 */
	public double width() {
		return width;
	}
	
	private void update(Object o) {
		setChanged();
		notifyObservers(o);
	}
	
}
