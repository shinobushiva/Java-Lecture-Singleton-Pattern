package pattern;

public class SingletonCheck {

	public static void main(String[] args) {
		SingletonUserConfig uc = SingletonUserConfig.getInstance();
		SingletonUserConfig uc2 = SingletonUserConfig.getInstance();

		System.out.println(uc);
		System.out.println(uc2);

	}

}
