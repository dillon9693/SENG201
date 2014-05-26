package world;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import place.Room;
import thing.Thing;
import world.World;
import actor.Actor;
import actor.Creature;
import actor.Person;

/**
 * Controller for Worlds.  Responds to updates in the view, such as a
 * clicked button. Supports actions that manipulate the world and its
 * objects. Static methods connect the controller to the view.
 * If a person and place are selected then move allows relocation.
 * A person can take or drop a thing from their current location.
 * 
 *  Allows user to add new elements to the world.
 *  
 * @author Dillon Kerr
 *
 */
public class WorldControl {
	
	private static JFrame jf = new JFrame("World Control");

	/**
	 * Allows actor to move to selected room and
	 * adds new room to actors' history.
	 * @param o - room to be visited
	 * @param p - person to be moved to room
	 */
	public static void goToRoom(Object o, Actor p) {
		if (o instanceof Room) {
			if(p == null){
				//error if no actor selected
				JOptionPane.showMessageDialog(jf, "No actor selected.");
			} 
			else {
				Room selectedRoom = (Room)o;
				if(selectedRoom == p.location()){
					//error if actor is already in selected room
					JOptionPane.showMessageDialog(jf, p.name() + " is already in " + selectedRoom+".");
				}
				else {
					System.out.println("moving " + p.name() + " from " + 
							p.location() + " to " + (selectedRoom));
					p.moveTo(selectedRoom);
				}
			}
		} else {
			//error if no room selected
			JOptionPane.showMessageDialog(jf, "No Room selected.");
		}
		
	}
	
	/**
	 * Allows actor to take item from selected room and
	 * add it to its inventory
	 * @param r - room where item is
	 * @param p - person who is taking item
	 * @param o - item to be taken
	 */
	public static void takeItem(Room r, Actor a, Object o) {
		if(a == null){
			//error if no actor selected
			JOptionPane.showMessageDialog(jf, "Can't take: no Actor selected.");
			return;
		}
		//check if the actor is not a person
		if(!(a instanceof Person)){
			JOptionPane.showMessageDialog(jf, "Only people can take items.");
			return;
		}
		Person p = (Person)a;
		if(r == null) {
			//error if no room selected
			JOptionPane.showMessageDialog(jf, "Can't take: no Room selected.");
			return;
		} 
		else if(p.location() != r) {
			//error if person is not in same room as item
			JOptionPane.showMessageDialog(jf, "Must be in " + r + " to take this.");
			return;
		} else {	
			Thing selectedThing = (Thing)o;
			System.out.println(selectedThing);
			if(selectedThing != null) {
				p.take(selectedThing);
			}
			else {
				//error if no item selected
				JOptionPane.showMessageDialog(jf, "No item selected.");
			}
		}
	}
	
	/**
	 * Drops item in personss inventory into current room.
	 * @param p - person with item
	 * @param o - item to be dropped
	 */
	public static void dropItem(Actor a, Object o) {
		if (a == null)  {
			//error if no actor selected
			JOptionPane.showMessageDialog(jf, "Can't drop: no Actor selected.");
			return;
		} 
		if(!(a instanceof Person)){
			JOptionPane.showMessageDialog(jf, "Only people can drop items.");
			return;
		}
		Person p = (Person)a;
		Thing selectedThing = (Thing)o;
		if(selectedThing != null) {
			p.drop(selectedThing);
		}
		else {
			//error if no item selected
			JOptionPane.showMessageDialog(jf, "No item selected.");
		}
	}
	
	/**
	 * Adds a person to the world in a default room
	 * @param w - world where the person is to be added
	 */
	public static void addPerson(World w) {
		String name = "";
		while(name.length() == 0) {
			//get inputted name
			name = JOptionPane.showInputDialog("Name:");
			
			if(name.length() != 0) {
				Actor p = new Person(name);
				//add person to world in default room (start room)
				w.addActor(p);
				Room[] room = w.places().toArray(new Room[0]);
				p.moveTo(room[0]);
			}
			else {
				//error if no name entered
				JOptionPane.showMessageDialog(jf, "Please enter a name.");
			}
		}
	}	
	/**
	 * Adds a creature to the world in a default room
	 * @param w - world where the creature is to be added
	 */
	public static void addCreature(World w) {
		String name = "";
		String type = "";
		while(name.length() == 0) {
			//get inputted name
			name = JOptionPane.showInputDialog("Name:");
			
			if(name.length() != 0) {
				while(type.length() == 0){
					type = JOptionPane.showInputDialog("Type:");
					if(type.length() != 0) {
						Actor c = new Creature(name, type);
						//add person to world in default room (start room)
						w.addActor(c);
						Room[] room = w.places().toArray(new Room[0]);
						c.moveTo(room[0]);
					}
					else {
						JOptionPane.showMessageDialog(jf, "Please enter a type.");
					}
				}
			}
			else {
				//error if no name entered
				JOptionPane.showMessageDialog(jf, "Please enter a name.");
			}
		}
	}
	
	/**
	 * Adds a room to the world
	 * @param w - world where the item is to be added
	 */
	public static void addRoom(World w) {
		String label = "";
		int level = -10;
		String desc = "";
		while(label.length() == 0) {
			//get inputted label
			label = JOptionPane.showInputDialog("Label:");
			if(label.length() != 0) {
				while(level == -10) {
					/*try to convert inputted string to integer
					 * catch exception if invalid input (i.e. not an integer)
					*/
					try {
						//get inputted level
						level = Integer.parseInt(JOptionPane.showInputDialog("Level:"));
						while(desc.length() == 0) {
							//get inputted description
							desc = JOptionPane.showInputDialog(jf, "Description:");
							if(desc.length() != 0){
								Room r = new Room(label,level,desc);
								w.addRoom(r); //add room to world
							}
							else {
								//error if invalid description
								JOptionPane.showMessageDialog(jf, "Please enter a description.");
							}
						}
					}
					catch(NumberFormatException e){
						//error if invalid level
						JOptionPane.showMessageDialog(jf, "Please enter valid level");
					}
				}
			}
			else {
				//error if invalid label
				JOptionPane.showMessageDialog(jf, "Please enter label.");
			}
		}
	}
	
	/**
	 * Adds an item to the world
	 * @param w - world where the item is to be added
	 */
	public static void addItem(World w) {
		String name = "";
		String desc = "";
		
		while(name.length() == 0) {
			//get inputted name
			name = JOptionPane.showInputDialog("Name:");
			if(name.length() != 0) {
				while(desc.length() == 0) {
					//get inputted description
					desc = JOptionPane.showInputDialog("Description:");
					if(desc.length() != 0) {
						Thing t = new Thing(name, desc);
						w.addThing(t);
						//adds item to default room (start room)
						Room[] room = w.places().toArray(new Room[0]);
						room[0].add(t);
					}
					else {
						//error if description is not entered
						JOptionPane.showMessageDialog(jf, "Please enter a description.");
					}
				}
			}
			else {
				//error if name is not entered
				JOptionPane.showMessageDialog(jf, "Please enter a name.");
			}
		}
	}
	
	/**
	 * Shows past rooms visited by a person
	 * @param p - a person
	 */
	public static void getPersonHistory(Actor a) {
		if(a == null){
			JOptionPane.showMessageDialog(jf, "No Actor selected.");
			return;
		}
		if(!(a instanceof Person)){
			JOptionPane.showMessageDialog(jf, "Only people have history.");
			return;
		}
		Person p = (Person)a;
		JTextArea jt = new JTextArea();
		JScrollPane jsp = new JScrollPane(jt);
		for(int i = 0; i < p.history().size(); i++) {
			int j = i + 1;
			jt.append(j + ". " + p.history().get(i).toString() + "\n");
		}
		JOptionPane.showMessageDialog(jf, jsp, p.name() + "'s Room History", JOptionPane.PLAIN_MESSAGE);
	}

}
