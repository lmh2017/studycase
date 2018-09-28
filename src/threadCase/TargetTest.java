package threadCase;

import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * @author mh_liu
 *
 *
 *
 *1.写一个固定大小的阻塞队列，队列可以由数组来实现，
     当队列中没有对象时，获取对象的线程被阻塞，直到队列中添加了对象。
     当队列中的对象数量达到最大值时，添加对象的线程被阻塞，直到队列里有对象被取走。
  剩下的就是：
    写一个添加线程，循环添加；
    写一个取走线程，循环取走，取一对象，就要延时一下，再取下一个；
    测试运行结果。
 */

public class TargetTest {
	
	private Object[] objects;
	
	private ReentrantLock lock;
	
	int size;
	
	int putIndex;
	
	int takeIndex;
	
    private final Condition notEmpty;

    private final Condition notFull;
	
	public TargetTest(int cap) {
		objects = new Object[cap];
		lock = new ReentrantLock();
		notEmpty = lock.newCondition();
		notFull = lock.newCondition();
	}
	
	public int size() {
        lock.lock();
        try {
            return size;
        } finally {
            lock.unlock();
        }
	}
	
	public void push(Object o) throws InterruptedException {
		lock.lockInterruptibly();
		try {
			while(size == objects.length) {
				System.out.println("队列已满，无法入队，waiting");
				notFull.await();  
			}
			enElement(o);
		}finally {
			lock.unlock();
		}
	}
	
	public Object pop() throws InterruptedException {
		lock.lockInterruptibly();
		try {
			while (size == 0) {
				System.out.println("队列已空，无法出队，waiting");
				notEmpty.await();
				System.out.println("出队成功");
			}
			return deElement();
		}finally {
			lock.unlock();
		}
	}
	
	
	
	//入队
	public void enElement(Object o) {
		final Object[] items = this.objects; 
        items[putIndex] = o;
        if (++putIndex == items.length)
            putIndex = 0;
        size++;
        notEmpty.signal();
	}
	
	//出队
	public Object deElement() {
		final Object[] items = this.objects;
		Object item = items[takeIndex];
		items[takeIndex] = null;
		if(++takeIndex == items.length) {
			takeIndex = 0;
		}
		size--;
		notFull.signal();
		return item;
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		TargetTest tt = new TargetTest(10);
		
		BlockingQueue<Object> queue = new ArrayBlockingQueue<>(10);
		
		
//		for(int j=0;j<100;j++) {
//		
//			new Thread( 
//				()->{
//					
//						try {
//							System.out.println(queue.take());
//							
//
//						} catch (Exception e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//					}
//				
//			).start();
//		}
//		
//		for(int i=0;i<100;i++) {
//			new Thread( 
//					()->{
//						
//							try {
//								queue.put(UUID.randomUUID().toString().substring(0, 5));
//							} catch (InterruptedException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//						}
//						
//					
//				).start();
//		}
		
		for(int j=0;j<100;j++) {
			
			new Thread( 
				()->{
					
						try {
							System.out.println(tt.pop());
							

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				
			).start();
		}
		
		for(int i=0;i<100;i++) {
			new Thread( 
					()->{
						
							try {
								tt.push(UUID.randomUUID().toString().substring(0, 5));
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
					
				).start();
		}
	}

	
	
}
