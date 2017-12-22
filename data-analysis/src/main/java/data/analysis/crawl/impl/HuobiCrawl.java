package data.analysis.crawl.impl;

import java.util.Map;

import data.analysis.crawl.Crawl;
import data.analysis.utils.HuobiWebSocketUtils;

public class HuobiCrawl implements Crawl{

	@Override
	public Map<String, String> getData() {
		return HuobiWebSocketUtils.MAP_CUR_RESULT;
	}

}
