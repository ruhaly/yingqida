class Test3 {

	public static final int a = 6 / 3;

	static {
		System.out.println("FinalTest static block");
	}
}

public class T {
	public static void main(String[] args) {
		System.out.println(Test3.a);
	}
}
