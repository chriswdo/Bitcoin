package wd.java8.dept09;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Example9_2P194 {

	public void main(){
		//path会根据文件系统来拼接路径
		Path absolute = Paths.get("/", "home");
		absolute.resolve(Paths.get("/"));
	}
}
