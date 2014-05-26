package actor;

import place.Room;
import thing.Thing;
import java.util.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for the Person class. Tests the individual methods of
 * the class.
 * 
 * @author Dillon Kerr
 *
 */
public class PersonTest {

	private Person testPerson1;
	private Person testPerson2;
	private Room livingRoom;
	private Room kitchen;
	
	private Thing ball;
	private Thing toy;
	
	@Before
	public void setUp() throws Exception {
		testPerson1 = new Person("Dillon");
		testPerson2 = new Person("Bill");
		
		livingRoom = new Room("Living Room");
		kitchen = new Room("Kitchen");
		
		ball = new Thing("ball");
		toy = new Thing("toy");
		
		testPerson1.moveTo(livingRoom);
		testPerson1.take(ball);

	}
	
	/*
	 * Test whether person drops item with 2 items in inventory.
	 */
	@Test
	public void testDrop1() {
		Collection<Thing> testColl = new ArrayList<Thing>();
		testColl.add(ball);
		testPerson1.drop(toy);
		
		assertTrue(testColl.containsAll(testPerson1.inventory()));
	}
	
	/*
	 * Test if drop(t) returns null when person's inventory is empty
	 */
	@Test
	public void testDrop2() {
		assertNull(testPerson2.drop(toy));
	}
	
	/*
	 * Test if inventory() returns correct collection of inventory.
	 */
	@Test
	public void testInventory() {
		Collection<Thing> testColl = new ArrayList<Thing>();
		testColl.add(ball);
		
		assertTrue(testColl.containsAll(testPerson1.inventory()));
	}
	
	/*
	 * Test if location() returns person's correct location.
	 */
	@Test
	public void testLocation() {
		
		assertSame(livingRoom, testPerson1.location());
	}
	
	/*
	 * Test if method sets person's correct location.
	 */
	@Test
	public void testSetLocation() {
		testPerson1.setLocation(kitchen);
		
		assertSame(kitchen, testPerson1.location());
	}
	
	/*
	 * Test if moveTo() records person's correct location after being moved.
	 */
	@Test
	public void testMoveTo() {
		testPerson1.moveTo(kitchen);
		
		assertSame(kitchen, testPerson1.location());
		
	}
	
	/*
	 * Test if name() returns the correct name of the person.
	 */
	@Test
	public void testName() {
	
		assertSame("Dillon", testPerson1.name());
	}
	
	/*
	 * Test if method correctly sets persons' name.
	 */
	@Test
	public void testSetName() {
		testPerson1.setName("tony");
		
		assertSame("tony", testPerson1.name());
	}
	/*
	 * Test if user's inventory contains correct items after taking certain item.
	 */
	@Test
	public void testTake() {
		Collection<Thing> testColl = new ArrayList<Thing>();
		testColl.add(ball);
		testColl.add(toy);
		testPerson1.take(toy);
		
		assertTrue(testColl.containsAll(testPerson1.inventory()));
		
	}

}
