package world;
import java.util.*;

import actor.Actor;
import actor.Person;
import place.Room;
import thing.Thing;

/**
 * Class that models simple worlds which comprise of rooms, persons, and things. 
 * Currently stores these and can deliver to clients. Provides capability to add
 * new rooms, persons, and things to the world. Can be observed and can observe
 * other classes. Observes the individual parts of the world (Person, Room, Thing)
 * 
 * @author Dillon Kerr
 *
 */
public class World extends Observable implements Observer {
	
	private ArrayList<Actor> actors;
	private ArrayList<Thing> items;
	private ArrayList<Room> places;
	
	/**
	 * Constructor. Makes an empty world; not really very useful 
	 * unless more world-building methods are available.
	 */
	public World() {
		actors = new ArrayList<Actor>();
		items = new ArrayList<Thing>();
		places = new ArrayList<Room>();	
	}
	
	/**
	 * Constructor. Either makes an empty world or a demo world with sufficient 
	 * content to demonstrate the available functionality.
	 * @param makingDemo - Whether to build a demo world.
	 */
	public World(boolean makingDemo) {
		actors = new ArrayList<Actor>();
		items = new ArrayList<Thing>();
		places = new ArrayList<Room>();
		if(makingDemo) {
			demoWorld();
		}
	}
	
	/**
	 * Actors (persons) in the world in a (possibly) empty collection 
	 * of an appropriate class.
	 * @return Collection of actors.
	 */
	public Collection<Actor> actors() {
		return actors;
	}
	
	/**
	 * Items (things) in the world in a (possibly) empty collection 
	 * of an appropriate class.
	 * @return Collection of items.
	 */
	public Collection<Thing> items() {
		return items;
	}	
	
	/**
	 * Places (rooms) in the world in a (possibly) empty collection 
	 * of an appropriate class.
	 * @return Collections of places.
	 */	
	public Collection<Room> places() {
		return places;
	}
	
	/**
	 * Adds a new Person to the world.
	 * @param p - Person to be added
	 */
	public void addActor(Actor p) {
		actors.add(p);
		
		p.addObserver(this);
		setChanged();
		notifyObservers(p);
	}
	/**
	 * Adds a new Room to the world.
	 * @param r - Room to be added
	 */
	public void addRoom(Room r) {
		places.add(r);
		r.addObserver(this);
		setChanged();
		notifyObservers(r);
	}
	
	/**
	 * Adds a new Thing to the World
	 * @param t - Thing to be added
	 */
	public void addThing(Thing t) {
		items.add(t);
		t.addObserver(this);
		setChanged();
		notifyObservers(t);
	}
	
	
	/**
	 * Method that creates a demo world by adding actors, rooms and things.
	 */
	public void demoWorld() {
		Room livingRoom = new Room("Living Room", 1, "Relax here");
		Room bedroom = new Room("Bedroom", 2, "Sleep Here");
		Person bill = new Person("Bill");
		Person john = new Person("John");
		Person tony = new Person("Tony");
		Thing baseball = new Thing("baseball","Can play catch with it");
		Thing broom = new Thing("broom","Sweep the floor with it!");
		Thing lamp = new Thing("lamp","Light up the room");
		
		bill.moveTo(livingRoom);
		john.moveTo(bedroom);
		tony.moveTo(livingRoom);
		places.add(livingRoom);
		places.add(bedroom);
		actors.add(bill);
		actors.add(john);
		actors.add(tony);
		items.add(baseball);
		items.add(broom);
		items.add(lamp);
		livingRoom.add(baseball);
		livingRoom.add(broom);
		bill.take(baseball);
		bill.take(broom);
		livingRoom.add(lamp);
		
		for(int i=0; i<places.size(); i++) {
			places.get(i).addObserver(this);
		}
		for(int i=0; i<actors.size(); i++) {
			actors.get(i).addObserver(this);
		}
		for(int i=0; i<items.size(); i++) {
			items.get(i).addObserver(this);
		}
	}
	
	/**
	 * Sends update to observer
	 */
	public void update(Observable arg0, Object arg1) {
		System.out.println("Change with" + arg1.toString());
		setChanged();
		notifyObservers(arg1);
	}
}
