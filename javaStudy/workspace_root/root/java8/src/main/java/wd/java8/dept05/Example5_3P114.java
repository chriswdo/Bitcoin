package wd.java8.dept05;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;

import org.junit.Test;

public class Example5_3P114 {

	/**
	 * 自定义时间校正器 
	 * 下一个工作日
	 */
	@Test
	public void main(){
		TemporalAdjuster NEXT_WORKDAY = w -> {
			LocalDate result = (LocalDate) w;
			do{
				result = result.plusDays(1);
			}while(result.getDayOfWeek().getValue()>=6);
			return result;
		};
		LocalDate backToWork = LocalDate.now().with(NEXT_WORKDAY);
		System.out.println(backToWork);
	}
}
