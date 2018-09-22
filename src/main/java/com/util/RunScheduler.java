package com.util;

import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class RunScheduler {

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job job;

	public void run() {
		try {
			String dateParam = new Date().toString();
			JobParameters param = new JobParametersBuilder().addString("date", dateParam).toJobParameters();
			jobLauncher.run(job, param);
		} catch (Exception e) {
			log.info("unable to run scheduler {}", e);
		}
	}
}
