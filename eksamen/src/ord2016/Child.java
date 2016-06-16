package ord2016;

import java.util.ArrayList;
import java.util.Collection;

public class Child extends AbstractRelation implements Relation {

	protected Child(Gender gender) {
		super(gender);
	}

	@Override
	public Collection<Person> getRelativesOf(Person person) {
		Collection<Person> result = new ArrayList<>();
		addChildren(person, result);
		return result;
	}
	
	public final static Relation
		CHILDREN = new Child(null),
		DAUGHTERS = new Child(Gender.FEMALE),
		SONS = new Child(Gender.MALE),
		GRANDCHILDREN = CHILDREN.of(CHILDREN);
}
