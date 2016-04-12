package kont2010.types;

public class Types {

	class A {}
	interface G {}
	class B extends A implements G {}
	class C extends A {}
	
	public void main() {
		A a = new A();
		B b = new B();
		C c = new C();
		G g = null;
		
//		a = a; a = b; a = c; a = g;
//		b = a; b = b; b = c; b = g;
//		c = a; c = b; c = c; c = g;
//		g = a; g = b; g = c; g = g;
		
		print(a instanceof A); print(a instanceof B); print(a instanceof C); println(a instanceof G);
		print(b instanceof A); print(b instanceof B); print("b instanceof C"); println(b instanceof G);
		print(c instanceof A); print("c instanceof B"); print(c instanceof C); println(c instanceof G);
		print(g instanceof A); print(g instanceof B); print(g instanceof C); println(g instanceof G);
	}
	
	private void print(Object o, String suffix) {
		System.out.print(o + suffix);
	}
	private void print(Object o) {
		print(o, "\t");
	}
	private void println(Object o) {
		print(o, "\n");
	}
	
	public static void main(String[] args) {
		new Types().main();
	}
}
