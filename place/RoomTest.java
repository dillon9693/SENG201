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

	@Test
	public void testContents() {
		Collection<Thing> testColl = new ArrayList<Thing>();
		testColl.add(ball);
		testColl.add(broom);

		assertTrue(testColl.containsAll(testRoom.contents()));
	}

	@Test
	public void testContentsList() {
		Collection<Thing> testColl = new ArrayList<Thing>();
		testColl.add(ball);
		testColl.add(broom);
		
		fail("Not yet implemented");
		
		
	}

	@Test
	public void testDepth() {
		assertEquals(3.0, testRoom.depth(),.00001);
	}

	@Test
	public void testDescription() {
		assertSame("A test room", testRoom.description());
	}

	@Test
	public void testLabel() {
		assertSame("Room 1", testRoom.label());
	}

	@Test
	public void testLevel() {
		assertEquals(1, testRoom.level());
	}

	@Test
	public void testSetDescription() {
		testRoom.setDescription("Another test room");
		assertSame("Another test room", testRoom.description());
	}

	@Test
	public void testSetLabel() {
		testRoom.setLabel("Room 2");
		assertSame("Room 2", testRoom.label());
	}

	@Test
	public void testSetLevel() {
		testRoom.setLevel(5);
		assertEquals(5, testRoom.level());
	}

	@Test
	public void testSetSize() {
		testRoom.setSize(6.0, 8.0);
		assertEquals(6.0, testRoom.width(),.00001);
		assertEquals(8.0, testRoom.depth(),.00001);
	}

	@Test
	public void testWidth() {
		assertEquals(5.0, testRoom.width(), .00001);
	}

}
