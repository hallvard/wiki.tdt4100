package minegenkode;

import java.util.ArrayList;
import java.util.List;

public class PersonCorrected {
	private String name;
	
	private char gender;
	
	public final static char MALE = 'M';
	public final static char FEMALE = 'F';
	
	private PersonCorrected mother;
	private PersonCorrected father;
	
	private List<PersonCorrected> children = new ArrayList<>();
	
	public PersonCorrected(String name, char gender) {
		// Her manglet det opprinnelig innkapsling. Kan testes ved å sjekke at å
		// gi en ugyldig verdi for gender til konstruktøren utløser et unntak.
		if (gender != MALE && gender != FEMALE) {
			throw new IllegalArgumentException("Invalid gender provided to Person");
		}
		
		this.name = name;
		this.gender = gender;
	}
	
	public String getName() {
		return name;
	}
	
	private char getGender() {
		return gender;
	}
	
	public PersonCorrected getMother() {
		return mother;
	}
	
	public PersonCorrected getFather() {
		return father;
	}

	public int getChildCount() {
		return children.size();
	}

	public PersonCorrected getChild(int n) {
		if (n >= getChildCount() || n < 0) {
			throw new IndexOutOfBoundsException("Child index out of range");
		}
		
		return children.get(n);
	}
	
	private void checkGender(PersonCorrected person, char gender) {
		if (person != null && person.gender != gender) {
			throw new IllegalArgumentException("Wrong gender for parent");
		}
	}
	
	private void checkOwnParent(PersonCorrected person, PersonCorrected parent) {
		if (person == parent) {
			throw new IllegalArgumentException("A person cannot be its own parent");
		}
	}

	public void setMother(PersonCorrected person) {
		checkGender(person, FEMALE);
		checkOwnParent(this, person);
		
		if (mother != null && mother.equals(person)) {
			return;
		}
	
		if (mother != null) {
			mother.removeChild(this);
		}
		
		mother = person;
		
		if (mother != null) {
			mother.addChild(this);
		}
	}
	
	public void setFather(PersonCorrected person) {
		checkGender(person, MALE);
		checkOwnParent(this, person);
		
		if (father != null && father.equals(person)) {
			return;
		}
		
		if (father != null) {
			father.removeChild(this);
		}
		
		father = person;
		
		if (father != null) {
			father.addChild(this);
		}
	}
	
	public void addChild(PersonCorrected person) {
		// Det finnes ingen sjekk for at samme barn legges til flere ganger.
		// Dette kan oppdages med å legge til barn, for å så observere hva
		// som skjer. Det kan testes ved å sjekke at samme barn ikke eksisterer
		// flere ganger når man itererer over barna til en person.
		
		/**
		 * ------------------
		 * FORBEDRINGSFORSLAG
		 * ------------------
		 * 
		 * if (children.contains(person)) {
		 *   return;
		 * }
		 */
		
		children.add(person);
		
		if (getGender() == MALE) {
			person.setFather(this);
		} else if (getGender() == FEMALE) {
			person.setMother(this);
		}
	}
	
	public void removeChild(PersonCorrected person) {
		if (children.contains(person)) {
			children.remove(person);

			// Her settes både mor og far til null i stedet for å sjekke hvilket
			// forhold denne personen har til barnet. Dette kan for eksempel
			// testes ved å sjekke at moren gjenstår etter at man fjerner
			// barnet fra faren.
			person.setMother(null);
			person.setFather(null);
			
			/**
			 * ------------------
			 * FORBEDRINGSFORSLAG
			 * ------------------
			 * 
			 * if (this.equals(person.getMother())) {
			 *	person.setMother(null);
		     * else if (this.equals(person.getFather())) {
		     *  person.setFather(null);  
			 * }
		   	 */
		}
	}
}
