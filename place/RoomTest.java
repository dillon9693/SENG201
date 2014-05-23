package place;

import thing.Thing;

import java.util.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RoomTest {
	
	private Room testRoom;
	private Thing ball;
	private Thing broom;
	@Before
	public void setUp() throws Exception {
		testRoom = new Room("Room 1", 1, "A test room");
		
		ball = new Thing("ball");
		broom = new Thing("broom");
		
		testRoom.setSize(5.0, 3.0);

		testRoom.add(ball);
		testRoom.add(broom);
		
	}
	
	/*
	 * Test if item is added to room's contents.
	 */
	@Test
	public void testAdd() {
		Collection<Thing> testColl = new ArrayList<Thing>();

		Thing testChair = new Thing("chair", "A test chair");
		
		testColl.add(ball);
		testColl.add(broom);
		
		testColl.add(testChair);
		testRoom.add(testChair);
		
		assertTrue(testColl.containsAll(testRoom.contents()));
		
	}

	/*
	 * Test if contents() returns the correct items in the room.
	 */
	@Test
	public void testContents() {
		Collection<Thing> testColl = new ArrayList<Thing>();
		testColl.add(ball);
		testColl.add(broom);

		assertTrue(testColl.containsAll(testRoom.contents()));
	}
	
	/*
	 * Test if contentsList() returns an accurate string of the contents
	 */
	@Test
	public void testContentsList() {
		Collection<Thing> testColl = new ArrayList<Thing>();
		testColl.add(ball);
		testColl.add(broom);
		System.out.println(testRoom.contentsList());
		
		assertEquals(testRoom.contentsList(), "ball,broom,");
	}

	/*
	 * Test if depth() returns the correct initial depth of room.
	 */
	@Test
	public void testDepth() {
		assertEquals(3.0, testRoom.depth(),.00001);
	}
	
	/*
	 * Test if description() returns the correct initial description of room.
	 */
	@Test
	public void testDescription() {
		assertSame("A test room", testRoom.description());
	}

	/*
	 * Test if label() returns the correct initial label of room.
	 */
	@Test
	public void testLabel() {
		assertSame("Room 1", testRoom.label());
	}
	
	/*
	 * Test if level() returns the correct initial level of the room.
	 */
	@Test
	public void testLevel() {
		assertEquals(1, testRoom.level());
	}
	
	/*
	 * Test if setDescription() correctly changes the description of the room.
	 */
	@Test
	public void testSetDescription() {
		testRoom.setDescription("Another test room");
		assertSame("Another test room", testRoom.description());
	}

	/*
	 * Test if setLabel() correctly changes the label of the room.
	 */
	@Test
	public void testSetLabel() {
		testRoom.setLabel("Room 2");
		assertSame("Room 2", testRoom.label());
	}

	/*
	 * Test if setLevel() correctly changes the level of the room.
	 */
	@Test
	public void testSetLevel() {
		testRoom.setLevel(5);
		assertEquals(5, testRoom.level());
	}

	/*
	 * Test if setSize() correctly changes the size of the room.
	 */
	@Test
	public void testSetSize() {
		testRoom.setSize(6.0, 8.0);
		assertEquals(6.0, testRoom.width(),.00001);
		assertEquals(8.0, testRoom.depth(),.00001);
	}

	/*
	 * Test if width() returns the correct initial width of the room.
	 */
	@Test
	public void testWidth() {
		assertEquals(5.0, testRoom.width(), .00001);
	}

}
