package edu.sal.sali.ejb.cache;

public class PollingThread extends Thread {

	private static final long POLLING_INTERVAL = 	30000;
	private static final long EVENT_INTERVAL = 		300000;
	private long interval;
	private SALI_Cache cache;
	private boolean doRun;
	private SALI_CacheMode mode;

	public PollingThread(SALI_Cache cache) {
		super();

		doRun = true;
		this.cache = cache;
	}

	public PollingThread(SALI_Cache cache, SALI_CacheMode mode) {
		super();

		doRun = true;
		this.cache = cache;
		this.mode = mode;

		init();
	}

	private void init() {
		if (mode == SALI_CacheMode.POLLING) {
			interval = POLLING_INTERVAL;
		} else {
			if (mode == SALI_CacheMode.EVENT) {
				interval = EVENT_INTERVAL;
			}
		}
	}

	public void run() {

		while (doRun) {
			cache.updateAll();
			try {
				sleep(interval);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
