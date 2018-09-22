package com.processor;

import java.util.Date;
import java.util.Map;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class SecondProcessor implements ItemProcessor<Map<String, Object>, Map<String, Object>> {
	
	private ExecutionContext jobExecutionContext;

	@Override
	public Map<String, Object> process(Map<String, Object> m) throws Exception {
		Date d = new Date();
		m.put("Date", d);
		return m;
	}
	
	@BeforeStep
	public void beforeStep(StepExecution stepExecution) {
		this.jobExecutionContext = stepExecution.getJobExecution().getExecutionContext();
	}
}
