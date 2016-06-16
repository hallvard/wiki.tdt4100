package ord2016;

import java.util.ArrayList;
import java.util.Collection;

public class Sibling extends AbstractRelation implements Relation {
	
	protected Sibling(Gender gender) {
		super(gender);
	}

	@Override
	public Collection<Person> getRelativesOf(Person person) {
		Collection<Person> result = new ArrayList<>();
		addChildren(person.getFather(), result);
		addChildren(person.getMother(), result);
		result.remove(person);
		return result;
	}

	//

	public final static Relation
		SIBLINGS = new Sibling(null),
		SISTERS = new Sibling(Gender.FEMALE),
		BROTHERS = new Sibling(Gender.MALE);
}
