package kont2017.part4;

public class Course {

	// fordeler/ulemper med en slik implementasjon av en
	// klasse med data som ikke skal kunne endres

	public final String name, description;

	public Course(String name, String description) {
		// auto-generert av Eclipse, hva betyr super() her?
		super();
		this.name = name;
		this.description = description;
	}
}
