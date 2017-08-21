package kont2017.part3;

public abstract class MenuItem {

	private final String name, description;

	public MenuItem(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
}
