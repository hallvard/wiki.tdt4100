package ord2016;

import java.util.ArrayList;
import java.util.Collection;

public class Parent implements Relation {

	@Override
	public Collection<Person> getRelativesOf(Person person) {
		Collection<Person> result = new ArrayList<>();
		if (person.getMother() != null) {
			result.add(person.getMother());
		}
		if (person.getFather() != null) {
			result.add(person.getFather());
		}
		return result;
	}
	
	public final static Relation
		PARENTS = new Parent(),
		GRAND_PARENTS = PARENTS.of(PARENTS);	
}
