package wd.java8.lambda;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.swing.JButton;
/**
 * lambda表达式与 匿名类的区别
 * 1.	this关键字，
 * 			匿名类的this关键字指向这个匿名类
 * 			lambda表达式的this指向这个lambda表达式的包含类
 * 2.   编译方式
 * 			lambda表达式被编译成私有方法 	invokedynamic 字节码
 * 			匿名类是匿名类
 * @author wd
 *
 */

public class LambdaTest {

	public void RunnableUse(){
		new Thread(()->System.out.println("lambda test!"));
	}
	
	public void SwingUse(){
		JButton show = new JButton("show");
		show.addActionListener((e)->System.out.println("action!"));
	}
	
	public void CollectionUse(){
		List features = Arrays.asList("a","b","c");
		features.forEach(System.out::println);
	}
	
	public void PredicateUse(){
	    List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
	 
	    System.out.println("Languages which starts with J :");
	    filter(languages, (str)->((String)str).startsWith("J"));
	 
	    System.out.println("Languages which ends with a ");
	    filter(languages, (str)->((String)str).endsWith("a"));
	 
	    System.out.println("Print all languages :");
	    filter(languages, (str)->true);
	 
	    System.out.println("Print no language : ");
	    filter(languages, (str)->false);
	 
	    System.out.println("Print language whose length greater than 4:");
	    filter(languages, (str)->((String)str).length() > 4);
	    
	    Predicate<String> startsWithJ = (n) -> n.startsWith("J");
	    Predicate<String> fourLetterLong = (n) -> n.length() == 4;
	    languages.stream()
	        .filter(startsWithJ.and(fourLetterLong))
	        .forEach((n) -> System.out.print("nName, which starts with 'J' and four letter long is : " + n));
	}
	 
	public  void filter(List <String>names, Predicate condition) {
	    for(String name: names)  {
	        if(condition.test(name)) {
	            System.out.println(name + " ");
	        }
	    }
	}
	
	public void MapUse(){
		// 使用lambda表达式
		List <Integer>costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
		costBeforeTax.stream().map((cost) -> cost + .12*cost).forEach(System.out::println);
	}
	
	public void ReduceUse(){
		// 新方法：
		List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
		double bill = costBeforeTax.stream().reduce((sum, cost) -> sum + cost).get();
		System.out.println("Total : " + bill);
	}
	
	public void FilterUse(){
		List<String> strList = Arrays.asList("a","b","c","d");
		// 创建一个字符串列表，每个字符串长度大于2
		List<String> filtered = strList.stream().filter(x -> x.length()> 2).collect(Collectors.toList());
		System.out.printf("Original List : %s, filtered list : %s %n", strList, filtered);
	}
	
	public void StreamUse(){
		List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "U.K.","Canada");
		String G7Countries = G7.stream().map(x -> x.toUpperCase()).collect(Collectors.joining(", "));
		System.out.println(G7Countries);
		
		List<Integer> numbers = Arrays.asList(9, 10, 3, 4, 7, 3, 4);
		List<Integer> distinct = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
		System.out.printf("Original List : %s,  Square Without duplicates : %s %n", numbers, distinct);
		
		//获取数字的个数、最小值、最大值、总和以及平均值
		List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
		IntSummaryStatistics stats = primes.stream().mapToInt((x) -> x).summaryStatistics();
		System.out.println("Highest prime number in List : " + stats.getMax());
		System.out.println("Lowest prime number in List : " + stats.getMin());
		System.out.println("Sum of all prime numbers : " + stats.getSum());
		System.out.println("Average of all prime numbers : " + stats.getAverage());
		
		
	}
}
