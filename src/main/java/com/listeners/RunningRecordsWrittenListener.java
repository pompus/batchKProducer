package com.listeners;

import org.springframework.batch.core.listener.ChunkListenerSupport;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.scope.context.StepContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

/**
 * @author HP PC
 *
 *         either extends ChunkListenerSupport or implements ChunkListener
 */
@Component
@Log4j2
public class RunningRecordsWrittenListener extends ChunkListenerSupport {

	@Value("${runningRecordsWrittenInterval:1}")
	@Autowired(required = false)
	private int interval;

	@Override
	public void afterChunk(ChunkContext context) {
		StepContext cx = context.getStepContext();
		int r = cx.getStepExecution().getReadCount();
		int w = cx.getStepExecution().getWriteCount();

		if (w % 1 == 0) {
			log.info("records read {} , write {} ", r, w);
		}
	}
}
