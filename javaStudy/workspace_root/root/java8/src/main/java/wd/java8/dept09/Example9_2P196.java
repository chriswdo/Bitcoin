package wd.java8.dept09;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

public class Example9_2P196 {

	@Test
	public void main() throws IOException{
		Path path = Paths.get("E:","aaa.txt");
		byte[] bytes = Files.readAllBytes(path);
		String str = new String(bytes,"gbk");
		System.out.println(str);
	}
}
