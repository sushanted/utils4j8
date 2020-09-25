package sr.utils.utils4j8;

import static sr.utils.utils4j8.Utils4j8.collection;
import static sr.utils.utils4j8.Utils4j8.function;
import static sr.utils.utils4j8.Utils4j8.predicate;
import static sr.utils.utils4j8.Utils4j8.safeCast;
import static sr.utils.utils4j8.Utils4j8.valueAt;
import static sr.utils.utils4j8.Utils4j8.valueOf;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * A demo of all capabilities of Utils4j8
 *
 */
public class Utils4j8Demo {

	public static void main(String[] args) {

		
		// Method  : safeCast 
		// Usecase : Cast an object into another type, with a prior instanceOf check

		// Verbose code looks like:

		System.out.println(//
				Optional.ofNullable("NewPerson")//
						.filter(Person.class::isInstance)//
						.map(Person.class::cast)//
						.orElse(new Person("NewPerson"))//
		);

		// With safeCast method, it becomes one liner and less verbose as follows
		// keeping the output exactly same.

		System.out.println(//
				Optional.ofNullable("NewPerson")//
						.map(safeCast(Person.class))//
						.orElse(new Person("NewPerson"))//
		);
		
		
		

		List<Person> persons = getSamplePersonList();

		// mapBy a property
		Map<String, Person> personMap = collection(persons).mapBy(Person::getName);

		// valueAt : safely get element at an index in the list
		Optional.ofNullable(persons)//
				.map(valueAt(1))//
				.ifPresent(System.out::println);

		Optional.ofNullable(personMap)//
				.map(valueOf("Sanjay"))//
				.ifPresent(System.out::println);

		Optional.ofNullable("vallue")//
				.filter(//
						predicate("value"::equals)//
								.onPass(v -> System.out.println("was " + v))
								.onFail(v -> System.out.println("wasn't " + v))//
				)//
				.orElse("NA");

		Optional.ofNullable("value")//
				.map(//
						function(x -> null)//
								.onNull(x -> System.out.println(x + " mapped to null")//
								)//
				)//
				.orElse("NA");

		// When you have to do the same operation(s) on the default value as the
		// original value
		Optional.ofNullable("value")//
				.map(//
						function(x -> (String) null)//
								.onNullSwitchTo("defaultValue")//
				)//
				.map(String::toUpperCase)//
				.ifPresent(System.out::println);

	}

	static List<Person> getSamplePersonList() {
		return Arrays.asList(//
				new Person()//
						.setName("Ajay")//
						.setAddress(//
								new Address()//
										.setLine1("A-90, Tirth Towers")//
										.setLine2("Dhanori")//
										.setZipCode("411023")//
						), //
				new Person()//
						.setName("Vijay")//
						.setAddress(//
								new Address()//
										.setLine1("F-13, Global Society")//
										.setLine2("Baner")//
										.setZipCode("411045")), //
				new Person()//
						.setName("Sanjay")//
						.setAddress(//
								new Address()//
										.setLine1("567, Mi Casa")//
										.setLine2("Hadapsar")//
										.setZipCode("411045"))//
		);
	}

	static class Person {

		private String name;
		private Address address;

		public Person() {

		}

		public Person(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public Person setName(String name) {
			this.name = name;
			return this;
		}

		public Address getAddress() {
			return address;
		}

		public Person setAddress(Address address) {
			this.address = address;
			return this;
		}

		@Override
		public String toString() {
			return "Person [name=" + name + ", address=" + address + "]";
		}

	}

	static class Address {

		private String line1;
		private String line2;
		private String zipCode;

		public String getLine1() {
			return line1;
		}

		public Address setLine1(String line1) {
			this.line1 = line1;
			return this;
		}

		public String getLine2() {
			return line2;
		}

		public Address setLine2(String line2) {
			this.line2 = line2;
			return this;
		}

		public String getZipCode() {
			return zipCode;
		}

		public Address setZipCode(String zipCode) {
			this.zipCode = zipCode;
			return this;
		}

		@Override
		public String toString() {
			return "Address [line1=" + line1 + ", line2=" + line2 + ", zipCode=" + zipCode + "]";
		}

	}

}
