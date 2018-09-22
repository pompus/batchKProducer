package com.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepListenerSupport;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author HP PC 
 * 		   https://www.javainuse.com/spring/batchtaskchunk
 *         https://docs.spring.io/spring-batch/trunk/reference/html/patterns.html#passingDataToFutureSteps
 */
@Component
public class SetUpTasklet extends StepListenerSupport implements Tasklet {

	@Value("${PARAM_KEY}")
	private String keyName;

	private StepExecution stepExecution;

	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		ExecutionContext executionContext = this.stepExecution.getExecutionContext();
		executionContext.put(keyName, keyName);
		return RepeatStatus.FINISHED;
	}

	@Override
	public void beforeStep(StepExecution stepExecution) {
		this.stepExecution = stepExecution;
	}
}
