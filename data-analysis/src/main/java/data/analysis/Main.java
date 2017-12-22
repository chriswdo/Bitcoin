package data.analysis;

import java.util.Map;

import data.analysis.crawl.Crawl;
import data.analysis.crawl.impl.BittrexCrawl;
import data.analysis.crawl.impl.HuobiCrawl;
import data.analysis.crawl.impl.PoloniexCrawl;
import data.analysis.utils.HuobiWebSocketUtils;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		HuobiWebSocketUtils.executeWebSocket();
		while(true){
			Thread.sleep(2000);
			Crawl huobiCrawl = new HuobiCrawl();
			Crawl bittrexCrawl = new BittrexCrawl();
			Crawl polniexCrawl = new PoloniexCrawl();
			Map<String,String>huibimap = huobiCrawl.getData();
			System.out.println("huobi   "+"BTC="+huibimap.get("btc")+"   "+"BCC="+huibimap.get("bcc")+"   "+"ETH="+huibimap.get("eth")+"   ");
			Map<String,String>bittrmap = bittrexCrawl.getData();
			System.out.println("bittr   "+"BTC="+bittrmap.get("btc")+"   "+"BCC="+bittrmap.get("bcc")+"   "+"ETH="+bittrmap.get("eth")+"   ");
			Map<String,String>polonmap = polniexCrawl.getData();
			System.out.println("polon   "+"BTC="+polonmap.get("btc")+"   "+"BCC="+polonmap.get("bcc")+"   "+"ETH="+polonmap.get("eth")+"   ");
			
		}
	}
}
