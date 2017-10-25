import java.util.List;

import org.junit.Test;

public class CollectionsNullTest {

	@Test
	public void main(){
		List list = null;
		for(Object o : list){
			System.out.println(o);
		}
	}
}
