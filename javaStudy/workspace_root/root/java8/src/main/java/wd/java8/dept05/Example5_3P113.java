package wd.java8.dept05;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import org.junit.Test;

public class Example5_3P113 {

	@Test
	public void main(){
		LocalDate firstTuesday = LocalDate.of(2017, 6, 15);
		firstTuesday.with(TemporalAdjusters.nextOrSame(DayOfWeek.TUESDAY));
	}
}
