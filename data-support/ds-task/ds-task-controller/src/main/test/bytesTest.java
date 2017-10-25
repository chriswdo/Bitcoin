import java.io.UnsupportedEncodingException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.eshore.datasupport.task.pojo.Rwsl;
import com.eshore.datasupport.task.service.IRwslService;

public class bytesTest {

    @Test  
    public void test() throws UnsupportedEncodingException{
    	byte [] bytes = "测试数据".getBytes("UTF-8");
    	for(byte b : bytes){
    		System.out.println(b);
    	}
    	System.out.println(bytes);
    }
}