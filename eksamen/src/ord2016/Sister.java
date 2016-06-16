package ord2016;

import java.util.ArrayList;
import java.util.Collection;

public class Sister implements Relation {
	
	private void addChildren(Person person, Collection<Person> result) {
		if (person != null) {
			for (Person child : person) {
				if (child.getGender() == Gender.FEMALE && (! result.contains(child))) {
					result.add(child);
				}
			}
		}
	}

	@Override
	public Collection<Person> getRelativesOf(Person person) {
		Collection<Person> result = new ArrayList<>();
		addChildren(person.getFather(), result);
		addChildren(person.getMother(), result);
		result.remove(person);
		return result;
	}
}
