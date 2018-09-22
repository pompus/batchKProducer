package com.listeners;

import java.util.Date;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepListenerSupport;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

/**
 * @author HP PC
 * 
 *         you can either extends StepListenerSupport or implements StepListener
 *
 * @param <S>
 * @param <T>
 */
@Component
@Log4j2
public class LogStartStopListener<S, T> extends StepListenerSupport<T, S> {

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		long elapsed = new Date().getTime() - stepExecution.getStartTime().getTime();
		log.info("end step {} , elapsed {} , summary {}", stepExecution.getStepName(), elapsed,
				stepExecution.getSummary());
				
		return super.afterStep(stepExecution);
	}

	@Override
	public void beforeStep(StepExecution stepExecution) {
		log.info("start step {} ", stepExecution.getStepName());
	}
}
