package minegenkode;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class PersonTest extends TestCase {
	private Person hallvard;
	private Person marit;

	private Person junior;

	@Before
	public void setUp() {
		hallvard = new Person("Hallvard", 'M');
		marit = new Person("Marit", 'F');
		junior = new Person("Junior", 'M');
	}

	@Before
	public void testConstructor() {
		try {
			new Person("Robin Hood", 'A');
			fail("Constructor should only allow the genders M and F");
		} catch (IllegalArgumentException e) {}
	}

	@Test
	public void testAddChild() {
		hallvard.addChild(junior);
		assertEquals("Adding a child should only add it once", hallvard.getChildCount(), 1);
	}

	@Test
	public void testAddChildTwice() {
		hallvard.addChild(junior);
		hallvard.addChild(junior);
		assertEquals("A child should not be added again if it has already been added",
				hallvard.getChildCount(), 1);
	}

	@Test
	public void testRemoveChild() {
		hallvard.addChild(junior);
		marit.addChild(junior);
		hallvard.removeChild(junior);
		assertEquals("Father should be null after removing son", junior.getFather(), null);
		assertEquals("Mother should not change when removing father", junior.getMother(), marit);
	}
}
	