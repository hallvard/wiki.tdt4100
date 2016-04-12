package ord2014.theory;

/*
 * @startuml
 * object MysticalObject0 {
 * 		number = 3
 * }
 * MysticalObject0 ..> MysticalObject0: getNumber() => 3
 * object MysticalObject1 {
 * 		number = -2
 * }
 * MysticalObject1 ..> MysticalObject1: getNumber() => -2
 * MysticalObject0 ..> MysticalObject1: step()
 *  
 * object MysticalObject2 {
 * 		number = 1
 * }
 * MysticalObject2 ..> MysticalObject2: getNumber() => 1
 * MysticalObject1 ..> MysticalObject2: step()
 *  
 * object MysticalObject3 {
 * 		number = 0
 * }
 * MysticalObject3 ..> MysticalObject3: getNumber() => 0
 * MysticalObject2 ..> MysticalObject3: step()
 * MysticalObject3 ..> MysticalObject3: step()
 * @enduml
 */

public class MysticalObject {

	private int number;
	
	public MysticalObject(int number) {
		this.number = number;
	}

	public void step() {
		number = -(number - (int) Math.signum(number));
	}
	
	public int getNumber() {
		return number;
	}
	
	public static void main(String[] args) {
		MysticalObject mysticalObject = new MysticalObject(3);
		for (int i = 0; i < 10; i++) {
			System.out.print(mysticalObject.getNumber() + " => ");
			mysticalObject.step();
			System.out.println(mysticalObject.getNumber());
		}
	}
}
