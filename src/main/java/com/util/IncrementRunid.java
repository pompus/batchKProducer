package com.util;

import java.util.Date;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersIncrementer;
import org.springframework.stereotype.Component;

@Component
public class IncrementRunid implements JobParametersIncrementer {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.batch.core.JobParametersIncrementer#getNext(org.
	 * springframework.batch.core.JobParameters)
	 * 
	 * generate unique params for each job else spring batch may treat 2 job as similar
	 * 
	 */
	@Override
	public JobParameters getNext(JobParameters parameters) {
		Date date = new Date();
		return new JobParametersBuilder().addDate("run.ts", date).toJobParameters();
	}
}
