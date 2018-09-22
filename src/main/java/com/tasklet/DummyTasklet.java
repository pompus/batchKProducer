package com.tasklet;

import java.math.BigDecimal;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import lombok.extern.log4j.Log4j2;

/**
 * @author HP PC
 * https://www.javainuse.com/spring/batchtaskchunk
 *
 */
@Component
@Log4j2
public class DummyTasklet  implements Tasklet{
	
	@Autowired
	@Qualifier("outputDataSource")
	DataSource outputDataSource;
	
	@Value("${PARAM_KEY}")
	private String keyName;
	
	private ExecutionContext jobExecutionContext;
	
	private JdbcTemplate jdbcTemplate;
	
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		
		if (Optional.ofNullable(jobExecutionContext.get(keyName)).isPresent()) {
			Object o= jobExecutionContext.get(keyName);
			log.info("key {} ", o);
		}
		
		BigDecimal count= jdbcTemplate.queryForObject("select count(*) from target_profile" , BigDecimal.class);
		Assert.isTrue(count.intValue()==6, "there should be 6 record in target database"); 
		return RepeatStatus.FINISHED;
	}

	/* 
	 * pass executionContext from earlier step of job
	 */
	@BeforeStep
	public void beforeStep(StepExecution stepExecution) {
		this.jobExecutionContext = stepExecution.getJobExecution().getExecutionContext();
		
		if (!Optional.ofNullable(jdbcTemplate).isPresent()){
			this.jdbcTemplate= new JdbcTemplate(outputDataSource);
		}
	}
}
