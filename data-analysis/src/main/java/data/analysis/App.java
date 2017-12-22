package data.analysis;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

import data.analysis.utils.HttpsUtils;

/**
 * Hello world!
 *
 */
public class App 
{
	/**
	 	https://www.huobi.pro/zh-cn/market/
		https://www.bittrex.com/Home/Markets
		https://www.poloniex.com/exchange#btc_bch
	 * @param args
	 * @throws IOException 
	 * @throws MalformedURLException 
	 * @throws FailingHttpStatusCodeException 
	 */
//    public static void main( String[] args )
//    {
//    	try {
//    		Map<String,String> headers = new HashMap<>();
//    		headers.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; W…) Gecko/20100101 Firefox/56.0");
//			String ret = HttpsUtils.get("https://www.huobi.pro/zh-cn/market/", headers, null, null);
//			System.out.println(ret);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//    }
	
//    public static void main( String[] args ) throws FailingHttpStatusCodeException, MalformedURLException, IOException
//    {

//		final WebClient webClient=new WebClient();
//		webClient.getOptions().setCssEnabled(true);
//		webClient.getOptions().setJavaScriptEnabled(true);
//		final HtmlPage page=webClient.getPage("https://www.baidu.com");
//		System.out.println(page.asText());
//		webClient.close();

        // 得到浏览器对象，直接New一个就能得到，现在就好比说你得到了一个浏览器了  
//            WebClient webclient = new WebClient();  
          
            // 这里是配置一下不加载css和javaScript,配置起来很简单，是不是  
//            webclient.getOptions().setCssEnabled(false);  
//            webclient.getOptions().setJavaScriptEnabled(false);  
          
            // 做的第一件事，去拿到这个网页，只需要调用getPage这个方法即可  
//            HtmlPage htmlpage = webclient.getPage("https://www.huobi.pro/zh-cn/market/");  
          
            // 根据名字得到一个表单，查看上面这个网页的源代码可以发现表单的名字叫“f”  
//            final HtmlForm form = htmlpage.getFormByName("f");  
//            // 同样道理，获取”百度一下“这个按钮  
//            final HtmlSubmitInput button = form.getInputByValue("百度一下");  
//            // 得到搜索框  
//            final HtmlTextInput textField = form.getInputByName("q1");  
//            // 最近周星驰比较火呀，我这里设置一下在搜索框内填入”周星驰“  
//            textField.setValueAttribute("周星驰");  
//            // 输入好了，我们点一下这个按钮  
//            final HtmlPage nextPage = button.click();  
//            // 我把结果转成String  
//            String result = nextPage.asXml();  
              
//            System.out.println(htmlpage.asXml());  


//    }
	
    public static void main( String[] args )
    {
    	try {
    		Map<String,String> headers = new HashMap<>();
    		headers.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; W…) Gecko/20100101 Firefox/56.0");
			String ret = HttpsUtils.get("https://www.huobi.pro/zh-cn/market/", headers, null, null);
			System.out.println(ret);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    

}
