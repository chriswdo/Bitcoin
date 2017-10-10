package wd.java8.dept09;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Example9_1P191 {

	public void main() {
		/**
		 * in 会在代码运行完后关闭
		 */
		try(Scanner in = new Scanner(Paths.get("dfa"))){
			while(in.hasNext()){
				System.out.println("aaaa");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
