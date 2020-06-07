package com.app.services;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

@Service("DeferredService")
public class DeferredResultService implements Runnable {

	
	private final BlockingQueue<DeferredResult<ResponseEntity<String>>> resultQueue = new LinkedBlockingQueue<>(2);
	private final BlockingQueue<String> messageQueue = new LinkedBlockingQueue<>(100);
	
	private Thread thread;

	private volatile boolean start = true;
	
	private boolean keepRunning = true;
	
	
	public void subscribe() {
		startThread();
	}
	private void startThread() {
		
		if (start) {
			synchronized (this) {
				if (start) {
					start = false;
					ExecutorService executorService = Executors.newSingleThreadExecutor();
					executorService.submit(this);
					executorService.shutdown();
					try {
					    if (!executorService.awaitTermination(800, TimeUnit.MILLISECONDS)) {
					        executorService.shutdownNow();
					       } 
					} catch (InterruptedException e) {
					    executorService.shutdownNow();
					}
				}
			}
		}
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(keepRunning) {
			try {
				System.out.println("waiting sampai ada nih");
				DeferredResult<ResponseEntity<String>> result = resultQueue.take();
				System.out.println("udah dapat satu ini");
				String m = messageQueue.take();
				System.out.println("dapat message");
				result.setResult(new ResponseEntity<String>(m, HttpStatus.OK));
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void update(DeferredResult<ResponseEntity<String>> deferredResult) {
		resultQueue.add(deferredResult);
	}
	
	public  void updateString(String message) {
		messageQueue.add(message);
	}

}
