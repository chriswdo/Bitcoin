import java.io.UnsupportedEncodingException;

import org.junit.Test;

public class EncodingTest {

	@Test
	public void main(){
		String fd_mm = "112233";
		try {
			System.out.println(fd_mm.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
