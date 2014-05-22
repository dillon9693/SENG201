package world;

import java.util.*;

import thing.Thing;
import actor.Person;
import place.Room;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

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
		testWorld = new World(true);
		
		bill = new Person("Bill");
		john = new Person("John");
		baseball = new Thing("baseball","Can play catch with it");
		broom = new Thing("broom","Sweep the floor with it!");
		livingRoom = new Room("Living Room", 1, "Relax here");
		bedroom = new Room("Bedroom", 2, "Sleep Here");
	}

	@Test
	public void testActors() {
		Collection<Person> testColl = new ArrayList<Person>();
		testColl.add(bill);
		testColl.add(john);
		
		assertTrue(testColl.containsAll(testWorld.actors()));
	}

	@Test
	public void testItems() {
		fail("Not yet implemented");
	}

	@Test
	public void testPlaces() {
		fail("Not yet implemented");
	}

}
