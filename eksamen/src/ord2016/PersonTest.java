package ord2016;

import junit.framework.TestCase;

public class PersonTest extends TestCase {

	public void testAddChild() {
		Gender female = Gender.valueOf("female"), male = Gender.valueOf("male");
		Person mother = new Person("Chris"); mother.setGender(female);
		Person father1 = new Person("Pat");  father1.setGender(male);
		Person father2 = new Person("Alex"); father2.setGender(male);
		Person child = new Person("Jean");

		mother.addChild(child);
		assertEquals(1, mother.getChildCount());
		assertTrue(mother.hasChild(child));
		assertEquals(mother, child.getMother());
		mother.addChild(child);
		assertEquals(1, mother.getChildCount());
		
		father1.addChild(child);
		assertTrue(father1.hasChild(child));
		assertEquals(father1, child.getFather());

		father2.addChild(child);
		assertFalse(father1.hasChild(child));
		assertTrue(father2.hasChild(child));
		assertEquals(father2, child.getFather());
		
		father2.setGender(female);
		father2.addChild(child);
		// ???
		child.addChild(father2);
		// ???
	}
}
