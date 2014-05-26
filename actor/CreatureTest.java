package actor;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import place.Room;
import thing.Thing;

/**
 * Unit tests for the Creature class. Tests the individual methods
 * of the class
 * 
 * @author Dillon Kerr
 *
 */
public class CreatureTest {

	Creature testCreature1, testCreature2;
	Room livingRoom, kitchen;
	Thing ball, toy;
	
	@Before
	public void setUp() throws Exception {
		testCreature1 = new Creature("Woof","dog");
		
		livingRoom = new Room("Living Room");
		kitchen = new Room("Kitchen");
		
		ball = new Thing("ball");
		toy = new Thing("toy");
		
		testCreature1.moveTo(livingRoom);
	}

	/*
	 * Tests if method records person's correct location when moved.
	 */
	@Test
	public void testMoveTo() {
		testCreature1.moveTo(kitchen);
		assertSame(kitchen, testCreature1.location());
	}

	/*
	 * Test if method gets correct type of creature.
	 */
	@Test
	public void testGetType() {
		assertSame("dog", testCreature1.getType());
	}

	/*
	 * Test if method correctly sets type of creature.
	 */
	@Test
	public void testSetType() {
		testCreature1.setType("labrador");
		assertSame("labrador", testCreature1.getType());
	}

	/*
	 * Test if method gets correct location of creature.
	 */
	@Test
	public void testLocation() {
		assertSame(livingRoom, testCreature1.location());
	}

	/*
	 * Test if method correctly sets location of creature.
	 */
	@Test
	public void testSetLocation() {
		testCreature1.setLocation(kitchen);
		assertSame(kitchen, testCreature1.location());
	}

	/*
	 * Test if method gets correct name of creature.
	 */
	@Test
	public void testName() {
		assertSame("Woof",testCreature1.name());
	}

	/*
	 * Test if method correctly sets name of creature.
	 */
	@Test
	public void testSetName() {
		testCreature1.setName("Fido");
		assertSame("Fido", testCreature1.name());
	}

}
