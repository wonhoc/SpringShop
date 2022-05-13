package com.spring.shop.commons;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class PageGenerator {
	
	public Map<String, Object> pageGenerate(float totalCnt, float pagePerCnt, int curPage) {
		Map<String, Object> page = new HashMap<String, Object>();
		float offset = (curPage - 1) * pagePerCnt; 
		float limit = ((curPage -1 ) * pagePerCnt)+ pagePerCnt;
		page.put("offset", offset);
		page.put("limit", limit);
		page.put("totalCnt", totalCnt);
		return page;
	}
}