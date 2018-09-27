package threadCase;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PipedWriter;

public class ChannelSocket {
	
	public void writeData(PipedOutputStream out) throws IOException {
		System.out.println("准备开始写入数据");
		for(int i=0;i<100;i++) {
			out.write((""+i).getBytes());
		}
		out.close();
	}
	
	public void readData(PipedInputStream inputStream) throws IOException {
		byte[] bt = new byte[20];
		int length = inputStream.read(bt);
		while(length != -1) {
			System.out.println(new String(bt,0,length));
			length = inputStream.read(bt);
		}
		inputStream.close();
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		PipedInputStream inputStream = new PipedInputStream();
		PipedOutputStream outputStream = new PipedOutputStream();
		
		ChannelSocket channelSocket = new ChannelSocket();
		try {
			inputStream.connect(outputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		new Thread() {
			@Override
			public void run() {
				try {
					channelSocket.writeData(outputStream);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();
		
		Thread.sleep(1000);
		new Thread() {
			@Override
			public void run() {
				try {
					channelSocket.readData(inputStream);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();
	}

}
