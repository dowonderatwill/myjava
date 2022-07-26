package sizecheck;

import java.io.File;
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
		
		
		
		
	}
	
	public static void recurseNLevelDirSize(File folder, int curlevel) {
		
		if(curlevel > level ) { return; }
		System.out.println("Checked..."+folder.getAbsolutePath());
		boolean noSubDir = true;
		File[] all = folder.listFiles();
		for (File f : all) {
			if(!f.canRead()) {System.out.println("Can't read it:"+ f.getAbsolutePath()); continue;} 
			boolean isDir = f.isDirectory();
			if (isDir) {
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

}
