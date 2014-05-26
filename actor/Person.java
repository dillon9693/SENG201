package actor;

import java.util.*;

import place.Room;
import thing.Thing;

/**
 * Class that models people in worlds.
 * People have their own properties and can also go places and carry things.
 * @author Dillon Kerr
 *
 */
public class Person extends Actor {
	
	private ArrayList<Thing> inventory;
	private ArrayList<Room> history;
	
	/**
	 * Constructor
	 * @param name - Name of person
	 */
	public Person(String name) {
		inventory = new ArrayList<Thing>();
		super.setName(name);
		history = new ArrayList<Room>();
	}
	
	/**
	 * Remove item from inventory of person and add to room inventory.
	 * @param t - Thing to be removed.
	 * @return - Removed item.
	 */
	public Thing drop(Thing t) {
		if(inventory.size() == 0){
			return null;
		}
		inventory.remove(t);
		super.location().add(t);
		update(t);
		return t;
	}
	
	/**
	 * Inventory of person suitable for iteration by clients.
	 * @return - Collection containing inventory
	 */
	public Collection<Thing> inventory() {
		return inventory;
	}
	
	/**
	 * Set or change location of person and add to history
	 * @param destination - Determines new location of person.
	 */
	
	public void moveTo(Room destination) {
		super.setLocation(destination);
		history.add(destination);
		super.update(destination);
	}
	
	/**
	 * Add an item to inventory of a person. Remove it from the room contents.
	 * @param t - Item that will be taken
	 */
	public void take(Thing t) {
		inventory.add(t);
		super.location().remove(t);
		super.update(t);
	}
	
	/**
	 * Gets the history of the person.
	 * @return - List of rooms that have been visited.
	 */
	public ArrayList<Room> history() {
		return history;
	}
	
}
