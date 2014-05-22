package thing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ThingTest {

	private Thing testThing;
	
	@Before
	public void setUp() throws Exception {
		testThing = new Thing("Test Name", "Test Description");
	}

	@Test
	public void testDescription() {
		assertSame("Test Description", testThing.description());
	}

	@Test
	public void testName() {
		assertSame("Test Name", testThing.name());
	}

}
