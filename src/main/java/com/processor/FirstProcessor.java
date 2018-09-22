package com.processor;

import java.util.Map;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.model.Message;

@Component
public class FirstProcessor implements ItemProcessor<Map<String, Object>, Map<String, Object>> {

	@Value("${kafka.topic.key}")
	private String keyName;
	
	@Override
	public Map<String, Object> process(Map<String, Object> map) throws Exception {
		Message m = new Message();
		m.setUserId((String) map.get("userid"));
		m.setProfile((String) map.get("profile"));
		map.put(keyName, m);
		return map;
	}
}
