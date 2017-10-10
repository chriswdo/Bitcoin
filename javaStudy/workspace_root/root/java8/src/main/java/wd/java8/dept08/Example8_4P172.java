package wd.java8.dept08;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

import wd.java8.dept08.pojo.Person;

public class Example8_4P172 {

	@Test
	public void main(){
		List<Person> list = new ArrayList<Person>();
		Arrays.sort((Person[])list.toArray(), Comparator.comparing(Person::getName));
	}
}
