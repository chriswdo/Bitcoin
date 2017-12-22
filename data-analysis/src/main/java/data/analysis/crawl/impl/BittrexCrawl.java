package data.analysis.crawl.impl;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import data.analysis.crawl.Crawl;
import data.analysis.entity.BittrexResponse;
import data.analysis.entity.BittrexResponse.Result;
import data.analysis.utils.CommonUtils;
import data.analysis.utils.Cronts;
import data.analysis.utils.HttpsUtils;

/**
 * api网站
 * https://bittrex.com/Home/Api
 * @author wd
 *
 */
public class BittrexCrawl implements Crawl{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BittrexCrawl.class);
	
	private static final String BTC_URL="https://bittrex.com/api/v1.1/public/getticker?market=USDT-BTC";
	
	private static final String BCC_URL="https://bittrex.com/api/v1.1/public/getticker?market=USDT-BCC";
	
	private static final String ETH_URL="https://bittrex.com/api/v1.1/public/getticker?market=USDT-ETH";
	
	static Map<String,String> headers = new HashMap<>();
	static{
		headers.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; W…) Gecko/20100101 Firefox/56.0");
	}
	/**
	 * 
	 * @throws Exception
	 */
	private String getUSDTBTC() {
		String str = HttpsUtils.get(BTC_URL, headers, null, null);
		ObjectMapper mapper = new ObjectMapper();
		BittrexResponse response = null;
		try {
			response = mapper.readValue(str, BittrexResponse.class);
		} catch (Exception e) {
			 LOGGER.error( e.getMessage());
			 return "error";
		}
		Result result = response.getResult();
		return CommonUtils.formatPriceStr(result.getLast());
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	private String getUSDTBCC() {
		String str = HttpsUtils.get(BCC_URL, headers, null, null);
		ObjectMapper mapper = new ObjectMapper();
		BittrexResponse response = null;
		try {
			response = mapper.readValue(str, BittrexResponse.class);
		} catch (Exception e) {
			 LOGGER.error( e.getMessage());
			 return "error";
		}
		Result result = response.getResult();
		return CommonUtils.formatPriceStr(result.getLast());
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	private String getUSDTETH() {
		String str = HttpsUtils.get(ETH_URL, headers, null, null);
		ObjectMapper mapper = new ObjectMapper();
		BittrexResponse response = null;
		try {
			response = mapper.readValue(str, BittrexResponse.class);
		} catch (Exception e) {
			 LOGGER.error( e.getMessage());
			 return "error";
		}
		Result result = response.getResult();
		return CommonUtils.formatPriceStr(result.getLast());
	}
	
	public Map<String,String>getData(){
		Map<String,String>map = new HashMap<>();
		map.put(Cronts.BTC, getUSDTBTC());
		map.put(Cronts.BCC, getUSDTBCC());
		map.put(Cronts.ETH, getUSDTETH());
		return map;
	}
	
}
