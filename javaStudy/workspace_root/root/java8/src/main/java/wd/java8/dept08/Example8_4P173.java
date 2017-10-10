package wd.java8.dept08;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class Example8_4P173 {

	@Test
	public void main(){
		List<String> list = new ArrayList<String>();
		Collections.checkedCollection(list, String.class);
	}
}
