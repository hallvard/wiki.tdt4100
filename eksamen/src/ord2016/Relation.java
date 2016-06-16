package ord2016;

import java.util.Collection;

public interface Relation {

	/*
	 * Returns the collection of Persons related to the provided Person
	 * according to this Relation.
	 * E.g. if this Relation corresponds to the concept of niese,
	 * it should return all the Persons that are nieses of person.
	 */
	Collection<Person> getRelativesOf(Person person);

	default Collection<Person> of(Person person) {
		return getRelativesOf(person);
	}
	
	default Relation of(Relation rel) {
		return new Relation2(rel, this);
	}
}
