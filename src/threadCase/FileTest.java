package threadCase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileTest {
	
	
	public void copyFile(String source,String target) {
		File[] files = new File(source).listFiles();
		if(!new File(target).exists()) {
			new File(target).mkdirs();
		}
		
		for(File file : files) {
			if(file.isDirectory()) {
				copyFile(source+"//"+file.getName(),target+"//"+file.getName());
			}
			if(file.isFile()) {
				parseFile(source+"//"+file.getName(),target+"//"+file.getName());
			}
		}
		
	}

	private void parseFile(String string, String string2) {
		// TODO Auto-generated method stub
		FileInputStream fis = null;
		FileOutputStream fos = null;
		
		try {
			fis = new FileInputStream(string);
			fos = new FileOutputStream(string2);
			
			byte[] buffer = new byte[1024];
			int length = 0;
			while((length = fis.read(buffer))>0) {
				fos.write(buffer,0,length);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				fis.close();
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}		
		
	}
	
	public static void main(String[] args) {
		FileTest f = new FileTest();
		f.copyFile("/Users/mh_liu/book/test1", "/Users/mh_liu/book/test2/mmm");
	}

}
