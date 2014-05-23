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
public class Person extends Observable {
	
	private Collection<Thing> inventory;
	private Room location;
	private String name;
	
	/**
	 * Constructor
	 * @param name - Name of person
	 */
	public Person(String name) {
		inventory = new ArrayList<Thing>();
		location = null;
		this.name = name;
	}
	
	/**
	 * Remove item from inventory of person and add to room inventory.
	 * @param t - Thing to be removed.
	 * @return Removed item.
	 */
	public Thing drop(Thing t) {
		if(inventory.size() == 0){
			return null;
		}
		inventory.remove(t);
		location.add(t);
		update(t);
		return t;
	}
	
	/**
	 * Inventory of person suitable for iteration by clients.
	 * @return Inventory
	 */
	public Collection<Thing> inventory() {
		return inventory;
	}
	
	/**
	 * Tell others where we are.
	 * @return Current location of person (room).
	 */
	public Room location() {
		return location;
	}
	
	/**
	 * Set or change location of person
	 * @param destination - Determines new location of person.
	 */
	public void moveTo(Room destination) {
		location = destination;
		update(destination);
	}
	
	/**
	 * Tell others our name.
	 * @return Name of person.
	 */
	public String name() {
		return name;
	}
	
	/**
	 * Add an item to inventory of a person. Remove it from the room contents.
	 * @param t
	 */
	public void take(Thing t) {
		inventory.add(t);
		location.remove(t);
		update(t);
	}
	
	private void update(Object o) {
		setChanged();
		notifyObservers(o);
	}
	
	/**
	 * Person-specific version of toString().
	 */
	@Override
	public String toString() {
		String s = "Name: "+name+", Location: "+location;
		return s;
	}
}
