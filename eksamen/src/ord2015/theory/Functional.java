package ord2015.theory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Functional {

	Collection<Character> matchingCharacters1(Collection<Character> chars, Predicate<Character> predicate) {
		return chars.stream().filter(predicate).collect(Collectors.toList());
	}

	Collection<Character> matchingCharacters2(Collection<Character> chars, Predicate<Character> predicate) {
		Collection<Character> result = new ArrayList<Character>();
		for (Character c : chars) {
			if (predicate.test(c)) {
				result.add(c);
			}
		}
		return result;
	}
}
