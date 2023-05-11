package app;

public class Application {

	public static void main(String[] args) {

		new Thread(() -> {
			System.out.println("Ciao");
			System.out.println("Ciao");
		}).start();

		StringModifier wrapper = s -> "###" + s + "###";
		StringModifier addQuestionMark = s -> s + "?";
		StringModifier invert = s -> {
			String[] characters = s.split("");
			String inverted = "";
			for (int i = characters.length - 1; i >= 0; i--) {
				inverted += characters[i];
			}

			return inverted;
		};

		System.out.println(wrapper.modify("CIAO"));
		System.out.println(addQuestionMark.modify("CIAO"));
		System.out.println(invert.modify("CIAO"));


	}

}
