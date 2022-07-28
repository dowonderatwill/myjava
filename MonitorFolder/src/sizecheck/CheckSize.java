package sizecheck;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;

//bug for no access.


public class CheckSize {
	
	String root;
	static int level = 2;
	static HashMap<String,Long> map = new HashMap<>();
	
	public static void main(String[] args) {
				
		File dir = new File(args[0]);
		
		recurseNLevelDirSize(dir,0);
//		System.out.println(map.toString());
		String d = "./data";
		String f = new SimpleDateFormat("ddMMyyyy").format(new Date())+Instant.now().getNano();
		try {			save(d,f);			} catch (IOException e) 			{	System.out.println("Error in saving file"+e.getMessage());		}
		try {			map = load(d,f);	} catch (IOException e) 			{	System.out.println("Error in loading file"+e.getMessage());		}
											  catch (ClassNotFoundException e) 	{	System.out.println("Error in casting object"+e.getMessage());	}
//		System.out.println(map.toString());
		
	}
	
	public static void recurseNLevelDirSize(File folder, int curlevel) {
		
		if(curlevel > level ) { return; }
		
		boolean noSubDir = true;
		File[] all = folder.listFiles();
		for (File f : all) {
			if(!f.canRead()) {System.out.println("Can't read it:"+ f.getAbsolutePath()); continue;} 
			boolean isDir = f.isDirectory();
			
			if (isDir) {
				System.out.println("Checking..."+f.getAbsolutePath());
				curlevel ++;
				noSubDir = false;
				long sz = FileUtils.sizeOfDirectory(f) / (1024 * 1024); // original size is bytes, so converted to MB.
				map.put(f.getAbsolutePath(), sz);
				System.out.printf("[ %d\t]MB\t - %s", sz, f.getAbsolutePath());System.out.println();
				recurseNLevelDirSize(f, curlevel);
			}
		}
		if (noSubDir)
			return;
	}

	public static void save(String dir, String fn) throws IOException {
		Instant st = Instant.now();
		File d = new File(dir);
		if(!d.exists()) d.mkdir();
		File f = new File(d+"/"+fn);
		try(FileOutputStream fos = new FileOutputStream(f); ObjectOutputStream oos = new ObjectOutputStream(fos)){
			oos.writeObject(map);
			oos.flush();
		}
		Instant et = Instant.now();
		System.out.println("Completed save in [ "+Duration.between(st, et).toMillis()+" ] ms");
	}
	
	@SuppressWarnings("unchecked")
	public static HashMap<String,Long> load(String d, String f) throws IOException, ClassNotFoundException {
		Instant st = Instant.now();
		HashMap<String,Long> r = null;
		try(
		FileInputStream fis = new FileInputStream( d+"/"+f);
		ObjectInputStream ois = new ObjectInputStream(fis) ) {
			r = (HashMap<String,Long>)ois.readObject();
		}
		
		Instant et = Instant.now();
		System.out.println("Completed save in [ "+Duration.between(st, et).toMillis()+" ] ms");
		return r;
	}
}
