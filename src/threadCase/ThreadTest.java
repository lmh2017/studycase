package threadCase;

public class ThreadTest {
	
	
	
	
	public static void main(String[] args) {
		MyThread mt = new MyThread();
		MyThread mt1 = new MyThread();
		mt.start();
		mt1.start();
		Thread t = new Thread(mt);
		Thread t1 = new Thread(mt);
		Thread t2 = new Thread(mt);

		t.start();
		t1.start();
		t2.start();
		
		System.out.println(Thread.currentThread().getName());
	}
	
	static class MyThread extends Thread{
		
		public MyThread() {
			System.out.println(this.getName());
		}
		
		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName());
		}
		
	}
	

}
