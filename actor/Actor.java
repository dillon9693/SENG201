package actor;

import java.util.Observable;

import place.Room;

/**
 * Class that represents an actor in the world. Actors can move from room to
 * room in the world.
 * @author Dillon Kerr
 *
 */
public abstract class Actor extends Observable{
	
	private Room location;
	private String name;
	
	/**
	 * Tell others where we are.
	 * @return - Current location of actor (room).
	 */
	public Room location() {
		return location;
	}
	
	public void setLocation(Room destination) {
		this.location = destination;
	}
	
	/**
	 * Set or change location of actor
	 * @param destination - New location of actor.
	 */
	public abstract void moveTo(Room destination);
	
	/**
	 * Tell others our name.
	 * @return - Name of person.
	 */
	public String name() {
		return name;
	}
	
	/**
	 * Sets name of actor
	 * @param s - Name to be set
	 */
	public void setName(String s) {
		name = s;
	}
		
	/**
	 * Sends updates to observer
	 * @param o - Object that is updated
	 */
	protected void update(Object o) {
		setChanged();
		notifyObservers(o);
	}
	
	/**
	 * Actor-specific version of toString().
	 */
	@Override
	public String toString() {
		String s = "Name: "+name+", Location: "+location;
		return s;
	}
}
