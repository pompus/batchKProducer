package com.listeners;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

/**
 * @author HP PC
 * 
 *         either extends JobExecutionListenerSupport or implements
 *         JobExecutionListener
 *
 */
@Component
@Log4j2
public class BasicJobStatsListener implements JobExecutionListener {

	@Override
	public void afterJob(JobExecution job) {
		log.info("job ends -> job {} , status {} , stats {}", job.getJobInstance(), job.getExitStatus(), job);
		job.getStepExecutions().forEach(se -> log.info("step name {} , exist status {} , summary {}", se.getStepName(),
				se.getExitStatus(), se.getSummary()));
	}

	@Override
	public void beforeJob(JobExecution job) {
		log.info("job starts -> job {} , params {}", job.getJobInstance(), job.getJobParameters());
	}
}
