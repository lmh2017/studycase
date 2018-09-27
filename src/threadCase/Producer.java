package threadCase;

import java.util.Queue;
import java.util.UUID;

public class Producer implements Runnable{
	
	private Object lock;
	
	private Queue<String> queue;
	
	private int cap;
	
	public Producer(Object lock,Queue<String> queue,int cap) {
		this.lock = lock;
		this.queue = queue;
		this.cap = cap;
	}
	
	public void produce() {
		try {
			synchronized(lock) {
				while(queue.size() == cap) {
					lock.wait();
				}
				String s = UUID.randomUUID().toString();
				queue.offer(s);
				System.out.println("生产者生产："+s);
				lock.notifyAll();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		produce();
	}

}
