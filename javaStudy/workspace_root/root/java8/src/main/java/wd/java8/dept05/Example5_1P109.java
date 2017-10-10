package wd.java8.dept05;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

import org.junit.Test;

public class Example5_1P109 {

	/**
	 * instant
	 */
	@Test
	public void main(){
		Instant start = Instant.now();
		int i=0;
		while(i++<10000){};
		Instant end = Instant.now();
		Duration duration = Duration.between(start, end);
		duration.toMillis();
	}
}
