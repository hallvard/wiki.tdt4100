package ord2019.part2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * A patient has a set of conditions (of type String) that needs to be treated.
 * Implements Iterable to support iterating over all conditions.
 */
public class Patient implements Iterable<String> {

	private final Collection<String> conditions = new ArrayList<>();

	/**
	 * Add conditions to this patient, to indicate what s/he should be treated for.
	 * @param conditions
	 */
	public void addConditions(final String... conditions) {
		this.conditions.addAll(Arrays.asList(conditions));
	}

	/**
	 * Indicates if this patient has conditions that needs to be treated.
	 * @return true if this patient has conditions that needs to be treated, false otherwise.
	 */
	public boolean requiresTreatment( ) {
		return ! conditions.isEmpty();
	}

	/**
	 * Removes conditions from this patient, to indicate that these have been treated.
	 * @param conditions
	 */
	public void removeConditions(final Collection<String> conditions) {
		this.conditions.removeAll(conditions);
	}

	@Override
	public Iterator<String> iterator() {
		return conditions.iterator();
	}
}
