package ord2016;

import java.util.ArrayList;
import java.util.Collection;

public class Relation2 implements Relation {

	public Relation2(Relation rel1, Relation rel2) {
		this.rel1 = rel1;
		this.rel2 = rel2;
	}

	private final Relation rel1, rel2;
	
	@Override
	public Collection<Person> getRelativesOf(Person person) {
		Collection<Person> result1 = rel1.getRelativesOf(person);
		Collection<Person> result2 = new ArrayList<>();
		for (Person person1 : result1) {
			result2.addAll(rel2.getRelativesOf(person1));
		}
		return result2;
	}
	
	public final static Relation
		UNCLES = Sibling.BROTHERS.of(Parent.PARENTS),
		AUNTS = Sibling.SISTERS.of(Parent.PARENTS),
		NIESES = Child.DAUGHTERS.of(Sibling.SIBLINGS),
		NEPHEWS = Child.SONS.of(Sibling.SIBLINGS);
}
