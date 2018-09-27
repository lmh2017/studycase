package threadCase;

import java.util.LinkedList;
import java.util.Queue;

public class MainTest {
	
	public static void main(String[] args) throws InterruptedException {
		Object lock = new Object();
		Queue<String> queue = new LinkedList<>();
		Producer producer = new Producer(lock, queue,3);
		Customer customer = new Customer(lock, queue);
		
		new Thread(customer).start();
		Thread.sleep(1000);
		new Thread(producer).start();
		
		for(int i=0;i<10;++i) {
			new Thread(customer).start();
		}
		for(int i=0;i<10;i++) {
			new Thread(producer).start();
		}
		
	}
}
