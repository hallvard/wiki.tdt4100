package ord2016;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import junit.framework.TestCase;

public class FamilyTest extends TestCase {

	private Relation child, parent; 
	private Family family;
	
	@Override
	protected void setUp() throws Exception {
		child = Child.CHILDREN;
		parent = Parent.PARENTS;
		try {
			family = new Family();
			family.load(family.getClass().getResourceAsStream("hal.family"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void assertSame(Collection<Person> actual, Collection<Person> expected) {
		assertEquals("Expected " + expected + ", was " + actual, expected.size(), actual.size());
		assertTrue("Expected " + expected + ", was " + actual, expected.containsAll(actual));
	}
	private void assertSame(Collection<Person> actual, String... expected) {
		Collection<Person> persons = new ArrayList<>();
		for (int i = 0; i < expected.length; i++) {
			persons.add(family.findMember(expected[i]));
		}
		assertSame(actual, persons);
	}

	private void testRelation(Relation relation, String person, String... expected) {
		assertSame(relation.getRelativesOf(family.findMember(person)), expected);
	}

	public void testChild() {
		testRelation(child, "Hallvard Trætteberg", "Anne Trætteberg Reitan", "Jens Reitan Trætteberg");
		testRelation(Child.DAUGHTERS, "Mormor", "Aud", "Tante Solveig");
		testRelation(Child.SONS, "Bestefar", "Onkel Einar");
		testRelation(Child.SONS, "Aud", "Ola", "Hallvard Trætteberg");
		testRelation(Child.GRANDCHILDREN, "Aud", "Anne Trætteberg Reitan", "Jens Reitan Trætteberg", "Thorvald", "Audun", "Ingeborg");
	}
	
	public void testParent() {
		testRelation(parent, "Hallvard Trætteberg", "Aud", "Anton");
	}

	public void testOther() {
		testRelation(Relation2.UNCLES, "Hallvard Trætteberg", "Onkel Einar", "Onkel Jens");
		testRelation(Relation2.AUNTS, "Hallvard Trætteberg", "Tante Solveig");
		testRelation(Relation2.NIESES, "Aud", "Kusine Anne", "Madeleine", "Tone Elisabeth");
		testRelation(Relation2.NEPHEWS, "Hallvard Trætteberg", "Thorvald", "Audun");
	}
	
	public void testInverse() {
		testRelation(family.NIESES, "Aud", "Kusine Anne", "Madeleine", "Tone Elisabeth");
		testRelation(family.NEPHEWS, "Hallvard Trætteberg", "Thorvald", "Audun");
	}
}
