package entities;

import java.util.Random;

public class Student {

	// Attributi di istanza
	private String firstName;
	private String lastName;
	private String id;
	private int age;

	// Attributi statici
	static String school = "Epicode";

	// Costruttori
	public Student() { // COSTRUTTORE I
		this.firstName = "Giacomo";
		this.lastName = "Poretti";

	}

	public Student(String fn) { // COSTRUTTORE II
		setFirstName(fn);
		this.lastName = "Cognome Generico";
	}

	public Student(String fn, String ln) { // COSTRUTTORE III
		this(fn); // Richiamo il II Costruttore
		setLastName(ln);
		Random rndm = new Random();
		Integer i = rndm.nextInt();
		this.id = i.toString();
	}

	public Student(String fn, String ln, int a) { // COSTRUTTORE III
		this(fn, ln); // Richiamo il III Costruttore
		setAge(a);
	}

	// Getters & Setters

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setFirstName(String fn) {
		if (fn.equals("Ajeje")) {
			System.out.println("Ajeje non è un valore valido");
		} else {
			this.firstName = fn;
		}
	}

	public String getFirstName() {
		return this.firstName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		if (age < 0) {
			System.out.println("Età deve essere maggiore o uguale a zero");
		} else {
			this.age = age;
		}
	}

	// Metodi di istanza
	public void sayHello() {
		System.out.println("Ciao! Sono " + this.firstName);
	}

	public void sayHello(String something) {

		System.out.println("Ciao! Sono " + this.firstName);
	}

	// Metodi statici
	static void staticMethod() {
		System.out.println("Ciao sono un metodo statico!");
	}

	@Override
	public boolean equals(Object obj) {
		Student s = (Student) obj;
		if (this.firstName == s.firstName && this.lastName == s.lastName) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return this.firstName + " " + this.lastName;
	}

	// Overload
	public String toString(String s) {
		return s;
	}

}
