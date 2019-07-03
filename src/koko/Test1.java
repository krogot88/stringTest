package koko;

import java.util.ArrayList;
import java.util.List;

public class Test1 {
	private static int cycles = 1_000_000;
	private static List<String> listForPool = new ArrayList<String>();
	private static List<String> listForHeap = new ArrayList<String>();

	public static void main(String[] arg) throws InterruptedException {			
		System.out.println("pool time " + poolString());
		System.out.println("heap time " + heapString());

	}

	public static Long poolString() {	
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

	public static Long heapString() {		
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
