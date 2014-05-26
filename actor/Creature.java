package actor;

import place.Room;

/**
 * Another actor that can be in the world. A creature differs from a person in that
 * it cannot have an inventory and does not record its history.
 * 
 * @author Dillon Kerr
 *
 */
public class Creature extends Actor {
	
	String type;
	
	public Creature(String name, String type) {
		super.setName(name);
		this.type = type;
	}
	
	/**
	 * Moves creature to another room.
	 * @param destination - Room that creature moves to
	 */
	@Override
	public void moveTo(Room destination) {
		super.setLocation(destination);
		super.update(destination);
	}
	
	/**
	 * Gets the type of the Creature.
	 * @return - Type of the creature
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Sets the type of the creature.
	 * @param - New type
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * Creature-specific version of toString()
	 */
	public String toString() {
		String s = "Name: "+super.name()+", Location: "+super.location()+", Type: "+type;
		return s;
	}

}
