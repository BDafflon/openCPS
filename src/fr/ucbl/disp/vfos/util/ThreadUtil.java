package fr.ucbl.disp.vfos.util;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadUtil {

	private final static ExecutorService exec = Executors.newCachedThreadPool();
	
	public static void execute(Runnable r) {
		synchronized(ThreadUtil.class) {
			exec.execute(r);
		}
	}

}