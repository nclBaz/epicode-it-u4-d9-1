package app;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import entities.Student;

public class Application {

	private static Logger logger = (Logger) LoggerFactory.getLogger(Application.class);
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

		// ************************ PREDICATES **********************

		Predicate<Integer> isMoreThanTen = n -> n > 10;
		Predicate<Integer> isLessThanTwenty = n -> n < 20;
		Predicate<Integer> betweenTenAndTwenty = isMoreThanTen.and(isLessThanTwenty);

		System.out.println(isMoreThanTen.test(20));
		System.out.println(isMoreThanTen.test(9));
		System.out.println(isLessThanTwenty.test(20));
		System.out.println(isLessThanTwenty.test(9));

		System.out.println(isMoreThanTen.and(isLessThanTwenty).test(15));
		System.out.println(isMoreThanTen.and(isLessThanTwenty).test(25));
		System.out.println(isMoreThanTen.or(isLessThanTwenty).test(26));
		System.out.println(isMoreThanTen.negate().test(30));

		// ************************* LAMBDAS *************************
		Random rnd = new Random();

		Supplier<Integer> intSupplier = () -> rnd.nextInt(1, 100);

		for (int i = 0; i < 30; i++) {
			System.out.println(intSupplier.get());
		}

		List<Integer> intList = new ArrayList<>();
		for (int i = 0; i < 30; i++) {
			intList.add(intSupplier.get());
		}

//		intList.forEach(n -> System.out.println(n));

//		for (Integer integer : intList) {
//			System.out.println(integer);
//		}

		// ************************* STREAMS **************************
		System.out.println(intList);
		List<Integer> firsTenNumbers = intList.stream().limit(10).toList();
		Stream myStream = intList.stream().limit(10);
		System.out.println(firsTenNumbers);
//		myStream.forEach(i -> System.out.println(i));

		List<Integer> numbersLessThanTwenty = intList.stream().filter(isLessThanTwenty).toList();
		System.out.println(numbersLessThanTwenty);

		List<Integer> numbersGreaterOrEqualThanTwenty = intList.stream().filter(isLessThanTwenty.negate()).toList();
		System.out.println(numbersGreaterOrEqualThanTwenty);

		List<Integer> numbersBetweenTenAndTwenty = intList.stream().filter(betweenTenAndTwenty).toList();
		System.out.println(numbersBetweenTenAndTwenty);

		List<Integer> doubledNumbers = intList.stream().map(num -> num * 2).toList();
		System.out.println(doubledNumbers);

		List<Student> students = new ArrayList<>();
		students.add(new Student("Aldo", "Baglio", 20));
		students.add(new Student("Giovanni", "Storti", 30));
		students.add(new Student("Giacomo", "Poretti", 80));

		List<Student> youngStudents = students.stream().filter(student -> student.getAge() <= 30).toList();
		System.out.println(youngStudents);

		List<Integer> ages = students.stream().map(student -> student.getAge()).toList();
//		List<Integer> ages = students.stream().map(Student::getAge).toList();
		System.out.println(ages);

		List<Integer> youngAges = students.stream().filter(student -> student.getAge() <= 30)
				.map(student -> student.getAge()).toList();
		System.out.println(youngAges);

		if (students.stream().anyMatch(student -> student.getFirstName().equals("Aldo"))) {
			System.out.println("Nella lista c'è almeno un Aldo");
		}

		if (students.stream().allMatch(student -> student.getFirstName().equals("Aldo"))) {
			System.out.println("Nella lista sono tutti Aldo");
		}

		if (students.stream().allMatch(student -> student.getAge() >= 18)) {
			System.out.println("Nella lista sono tutti studenti maggiorenni");
		}

		int total = intList.stream().reduce(0, (acc, currNum) -> acc + currNum);
		System.out.println("Totale: " + total);

		int totalAges = students.stream().map(Student::getAge).reduce(0, (acc, currNum) -> acc + currNum);
		System.out.println("Età sommate: " + totalAges);

		// ************************************ LOCAL DATES ***********************
		LocalDate today = LocalDate.now();
		System.out.println(today);
		LocalDate tomorrow = today.plusDays(1);
		System.out.println(tomorrow);
		LocalDate yesterday = today.minusDays(1);
		System.out.println(yesterday);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
		LocalDate test = LocalDate.parse("11/05/2023 12:05:00", formatter);
		System.out.println(test);
		System.out.println(today.plusWeeks(1));
		System.out.println(today.isBefore(today.plusDays(1)));

		// ********************** LOGBACK ******************************

		logger.info("LOG TEST");

	}

}
