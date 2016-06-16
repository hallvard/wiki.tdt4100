package ord2016;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class Family {

	private Collection<Person> members = new ArrayList<>();
	
	/**
	 * Adds a Person as a new family member
	 * @param person the Person to add
	 */
	public void addMember(Person person) {
		members.add(person);
	}
	
	/**
	 * Finds a member with the given name
	 * @param name
	 * @return the Person in this Family with the provided name
	 */
	public Person findMember(String name) {
		for (Person person : members) {
			if (person.getName().equals(name)) {
				return person;
			}
		}
		return null;
	}

	//

	private void outputQuotedName(Person person, PrintWriter pw) {
		pw.print("\"" + person.getName() + "\"");		
	}
	
	/**
	 * Writes the contents of this Family to the OutputStream,
	 * so it can be reconstructed using load.
	 * @param out
	 * @throws IOException
	 */
	public void save(OutputStream out) throws IOException {
		PrintWriter pw = new PrintWriter(out);
		pw.println("# all persons");
		for (Person person : members) {
			pw.print(person.getGender());
			pw.print(" ");
			outputQuotedName(person, pw);
			pw.println();
		}
		pw.println();
		pw.println("# all mother/father-child relations");
		for (Person person : members) {
			if (person.iterator().hasNext()) {
				outputQuotedName(person, pw);
				for (Person child : person) {
					pw.print(" ");
					outputQuotedName(child, pw);
				}
				pw.println();
			}
		}
		pw.flush();
	}
	
	private static List<String> tokenize(String line) {
		List<String> tokens = new ArrayList<>();
		int pos = 0;
		while (pos < line.length()) {
			// is next token quoted?
			if (line.charAt(pos) == '"') {
				// find next quote and extract token
				int end = line.indexOf('"', pos + 1);
				tokens.add(line.substring(pos + 1, end));
				pos = end + 1;
			} else {
				// find next space and extract token
				int end = line.indexOf(' ', pos + 1);
				tokens.add(line.substring(pos, end));
				pos = end;
			}
			// skip spaces
			while (pos < line.length() && Character.isWhitespace(line.charAt(pos))) {
				pos++;
			}
		}
		return tokens;
	}
	
	/**
	 * Loads contents from the provided InputStream into this Family.
	 * @param in
	 * @throws IOException
	 */
	public void load(InputStream in) throws IOException {
		Scanner scanner = new Scanner(in);
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if (line.trim().length() == 0 || line.startsWith("#")) {
				continue;
			}
			List<String> tokens = tokenize(line);
			Gender gender = Gender.valueOf(tokens.get(0));
			if (gender != null) {
				// type 1 line
				Person person = new Person(tokens.get(1));
				person.setGender(gender);
				addMember(person);
			} else {
				// type 2 line
				Person person = findMember(tokens.get(0));
				for (int i = 1; i < tokens.size(); i++) {
					Person child = findMember(tokens.get(i));
					person.addChild(child);
				}
			}
		}
		scanner.close();
	}

	//

	private Collection<Person> getInverse(Relation rel, Person person, Gender gender, Iterable<Person> candidates) {
		Collection<Person> result = new ArrayList<>();
		for (Person candidate : candidates) {
			if ((gender == null || candidate.getGender() == gender) && rel.getRelativesOf(candidate).contains(person)) {
				result.add(candidate);
			}
		}
		return result;
	}

	/**
	 * Computes the persons in this Family for which
	 * the provided Person has a specific Relation to.
	 * If the gender argument is non-null,
	 * only persons having that gender is returned.
	 * E.g. if the relation corresponds to uncle and gender is FEMALE,
	 * the method will return all nieses of person
	 * @param rel - the specific Relation
	 * @param person - the specific Person
	 * @param gender - gender filter
	 * @return
	 */
	public Collection<Person> getInverse(Relation rel, Person person, Gender gender) {
		return getInverse(rel, person, gender, members);
	}

	public final Relation
		NIESES = (person) -> getInverse(Sibling.SIBLINGS.of(Parent.PARENTS), person, Gender.FEMALE),
		NEPHEWS = (person) -> getInverse(Sibling.SIBLINGS.of(Parent.PARENTS), person, Gender.MALE);

	//
	
	public static void main(String[] args) {
		Family family = new Family();
		try {
			family.load(family.getClass().getResourceAsStream("hal.family"));
			family.save(System.out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
