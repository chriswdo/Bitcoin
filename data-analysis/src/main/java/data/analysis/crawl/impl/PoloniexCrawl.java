package data.analysis.crawl.impl;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import data.analysis.crawl.Crawl;
import data.analysis.utils.CommonUtils;
import data.analysis.utils.Cronts;
import data.analysis.utils.HttpsUtils;

public class PoloniexCrawl  implements Crawl{
	private static final Logger LOGGER = LoggerFactory.getLogger(BittrexCrawl.class);
	
	public static final String URL = "https://poloniex.com/public?command=returnTicker";

	static Map<String,String> headers = new HashMap<>();
	static{
		headers.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; W…) Gecko/20100101 Firefox/56.0");
	}
	
	public Map<String,String> getData() {
		Map<String,String>retMap = new HashMap<>();
		String str = HttpsUtils.get(URL, headers, null, null);
		ObjectMapper mapper = new ObjectMapper();
		Map map = null;
		try {
			map = mapper.readValue(str, Map.class);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			retMap.put(Cronts.BTC, "error");
			retMap.put(Cronts.BCC, "error");
			retMap.put(Cronts.ETH, "error");
			return retMap;
		}
		retMap.put(Cronts.BTC, CommonUtils.formatPriceStr(getSpecialValue(map, "USDT_BTC")));
		retMap.put(Cronts.BCC, CommonUtils.formatPriceStr(getSpecialValue(map, "USDT_BCH")));
		retMap.put(Cronts.ETH, CommonUtils.formatPriceStr(getSpecialValue(map, "USDT_ETH")));
		return retMap;
	}
	
	private String getSpecialValue(Map map,String ticker){
		return (String) ((Map)map.get(ticker)).get("last");
		
	}
}
