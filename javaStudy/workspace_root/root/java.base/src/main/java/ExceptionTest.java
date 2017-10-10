
public class ExceptionTest {

	public void main(){
		try {
			throw new Exception();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		throw new RuntimeException();
	}
}
