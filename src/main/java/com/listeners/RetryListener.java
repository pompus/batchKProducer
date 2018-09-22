package com.listeners;

import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.listener.RetryListenerSupport;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

/**
 * @author HP PC
 * 
 *         either extends RetryListenerSupport or implements RetryListener
 *
 */
@Component
@Log4j2
public class RetryListener extends RetryListenerSupport {
	public <T, E extends Throwable> void onError(RetryContext context, RetryCallback<T, E> callback,
			Throwable throwable) {
		log.info("test");
	}
}
