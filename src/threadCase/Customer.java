package threadCase;

import java.util.Queue;

public class Customer implements Runnable{
	
	private Object lock;
	
	private Queue<String> queue;
	
	public Customer(Object lock,Queue<String> queue) {
		this.lock = lock;
		this.queue = queue;
	}
	
	public void custome() {
		synchronized (lock) {
			try {
				while(queue.isEmpty()) {
					System.out.println("消费者等待消费");
					lock.wait();
				}
				String s = queue.poll();
				System.out.println("消费者消费："+s);
				lock.notifyAll();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		custome();
	}

}
