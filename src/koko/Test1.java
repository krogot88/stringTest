package koko;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Test1 {
	private static int cycles = 1_000_000;

	public static void main(String[] arg) throws ExecutionException, InterruptedException {
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		Callable<Long> poolTest = new PoolTest(cycles);
		Callable<Long> heapTest = new HeapTest(cycles);
		Future<Long> timePoolTest = executorService.submit(poolTest);
		Future<Long> timeHeaptest = executorService.submit(heapTest);
		System.out.println("time poolTest: " + timePoolTest.get());
		System.out.println("time heapTest: " + timeHeaptest.get());
	}

}

class PoolTest implements Callable<Long> {
	private final int cycles;
	private static List<String> listForPool = new ArrayList<String>();

	public PoolTest(int cycles) {
		super();
		this.cycles = cycles;
	}

	@Override
	public Long call() throws Exception {
		String s = null;
		Long time = 0L;
		for (int i = 0; i < cycles; i++) {
			String temp = Integer.toString(i);
			long timeStart = System.currentTimeMillis();
			s = temp.intern();
			long timeEnd = System.currentTimeMillis();
			time = time + (timeEnd - timeStart);
			listForPool.add(s);
		}
		return time;
	}
}

class HeapTest implements Callable<Long> {
	private final int cycles;
	private static List<String> listForHeap = new ArrayList<String>();	

	public HeapTest(int cycles) {
		super();
		this.cycles = cycles;
	}

	@Override
	public Long call() throws Exception {
		String s = null;
		Long time = 0L;
		for (int i = 0; i < cycles; i++) {
			String temp = Integer.toString(i);			
			long timeStart = System.currentTimeMillis();
			s = new String(temp); 
			long timeEnd = System.currentTimeMillis();			
			time = time + (timeEnd - timeStart);
			listForHeap.add(s);
		}
		return time;
	}
}
