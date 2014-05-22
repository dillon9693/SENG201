package actor;

import place.Room;
import thing.Thing;
import java.util.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PersonTest {

	private Person testPerson;
	private Room livingRoom;
	private Room kitchen;
	
	private Thing ball;
	private Thing toy;
	
	@Before
	public void setUp() throws Exception {
		testPerson = new Person("Dillon");
		
		livingRoom = new Room("Living Room");
		kitchen = new Room("Kitchen");
		
		ball = new Thing("ball");
		toy = new Thing("toy");
		
		testPerson.moveTo(livingRoom);
		testPerson.take(ball);

	}

	@Test
	public void testDrop() {
		Collection<Thing> testColl = new ArrayList<Thing>();
		testColl.add(ball);
		testPerson.drop(toy);
		
		assertTrue(testColl.containsAll(testPerson.inventory()));
	}

	@Test
	public void testInventory() {
		Collection<Thing> testColl = new ArrayList<Thing>();
		testColl.add(ball);
		
		assertTrue(testColl.containsAll(testPerson.inventory()));
	}

	@Test
	public void testLocation() {
		
		assertSame(livingRoom, testPerson.location());
	}

	@Test
	public void testMoveTo() {
		testPerson.moveTo(kitchen);
		
		assertSame(kitchen, testPerson.location());
		
	}

	@Test
	public void testName() {
	
		assertSame("Dillon", testPerson.name());
	}

	@Test
	public void testTake() {
		Collection<Thing> testColl = new ArrayList<Thing>();
		testColl.add(ball);
		testColl.add(toy);
		testPerson.take(toy);
		
		assertTrue(testColl.containsAll(testPerson.inventory()));
		
	}

}
