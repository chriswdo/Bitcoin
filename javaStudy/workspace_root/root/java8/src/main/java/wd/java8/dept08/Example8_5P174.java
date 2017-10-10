package wd.java8.dept08;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.Test;

public class Example8_5P174 {

	@Test
	public void main() throws IOException{
		try(Stream <String>streams = Files.lines(Paths.get("C:/Users/wd/Desktop/test.txt"))){
			Optional <String>optional = streams.onClose(()->System.out.println("closed"))
			.filter(s->s.contains("base")).findFirst();
			if(optional.isPresent()){
				System.out.println(optional.get());
			}
		}
	}
	
//	public void main1(){
//		try(Stream <String>streams = Files.lines(Paths.get("C:/Users/wd/Desktop/test.txt"))
//				.onClose(()->System.out.println("closed"))
//				.filter(s->s.contains("base"))){
//
//		}
//	}
}
