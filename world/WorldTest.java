package world;

import java.util.*;

import thing.Thing;
import actor.Person;
import place.Room;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for the World class. Tests the individual methods of the
 * World class.
 * 
 * @author Dillon Kerr
 *
 */
public class WorldTest {
	
	private World testWorld;
	
	private Person bill;
	private Person john;
	private Thing baseball;
	private Thing broom;
	private Room livingRoom;
	private Room bedroom;
	@Before
	public void setUp() throws Exception {
		testWorld = new World();
		
		bill = new Person("Bill");
		john = new Person("John");
		baseball = new Thing("baseball","Can play catch with it");
		broom = new Thing("broom","Sweep the floor with it!");
		livingRoom = new Room("Living Room", 1, "Relax here");
		bedroom = new Room("Bedroom", 2, "Sleep Here");
	}

	/*
	 * Test if actors() returns the correct list of actors in the world.
	 */
	@Test
	public void testActors() {
		Collection<Person> testColl = new ArrayList<Person>();
		testColl.add(bill);
		testColl.add(john);
		testWorld.addActor(bill);
		testWorld.addActor(john);
		
		assertTrue(testColl.containsAll(testWorld.actors()));
	}

	/*
	 * Test if items() returns the correct list of items in the world.
	 */
	@Test
	public void testItems() {
		Collection<Thing> testColl = new ArrayList<Thing>();
		testColl.add(baseball);
		testColl.add(broom);
		testWorld.addThing(baseball);
		testWorld.addThing(broom);
		
		assertTrue(testColl.containsAll(testWorld.items()));
	}

	/*
	 * Test if places() returns the correct list of places in the world.
	 */
	@Test
	public void testPlaces() {
		Collection<Room> testColl = new ArrayList<Room>();
		testColl.add(livingRoom);
		testColl.add(bedroom);
		testWorld.addRoom(livingRoom);
		testWorld.addRoom(bedroom);
		
		assertTrue(testColl.containsAll(testWorld.places()));
	}
	
	/*
	 * Test if addPerson() successfully adds a new Person to the world.
	 */
	@Test
	public void testAddActor() {
		Collection<Person> testColl = new ArrayList<Person>();
		testColl.add(bill);
		testWorld.addActor(bill);
		
		assertTrue(testColl.containsAll(testWorld.actors()));
	}
	
	/*
	 * Test if addRoom() successfully adds a new Room to the world.
	 */
	@Test
	public void testAddRoom() {
		Collection<Room> testColl = new ArrayList<Room>();
		testColl.add(livingRoom);
		testWorld.addRoom(livingRoom);
		
		assertTrue(testColl.containsAll(testWorld.places()));
	}
	
	/*
	 * Test if addThing() successfully adds a new Thing to the world.
	 */
	@Test
	public void testAddThing() {
		Collection<Thing> testColl = new ArrayList<Thing>();
		testColl.add(baseball);
		testWorld.addThing(baseball);
		
		assertTrue(testColl.containsAll(testWorld.items()));
	}

}
