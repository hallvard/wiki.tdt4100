package ord2010.part2;

public class Main {

	public static void main(String[] args) {
		{
			A a = new B();
			B b = new A();
			G g1 = new A(), g2 = new B(), g3 = new C();
		}
		{
			A a; B b; C c; G g;
			boolean flag = a.methodA() == c.methodC(a);
			b.methodA().toString();
			b.methodG(new B());
			((G) a).methodG(a);
			((B) a).methodG(b);
			((C) a).methodG(a);
		}
	}
}
