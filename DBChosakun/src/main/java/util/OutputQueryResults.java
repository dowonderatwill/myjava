package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import queries.Queries;

public class OutputQueryResults {
	
	HashMap<String,List<ArrayList<String>>> allq = new HashMap<String,List<ArrayList<String>>>();
	HashMap<String,List<String>> colnames = new HashMap<String, List<String>>();
	
	public OutputQueryResults(Queries q) {
		this.allq = q.getAllQueryResults();
		this.colnames = q.getAllQueryColumnNames();
	}
	
	public void saveResultInAFile(String pfx) throws FileNotFoundException {
	
		DateTimeFormatter timeStampPattern = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String suffix = timeStampPattern.format(java.time.LocalDateTime.now());
        System.out.println("OutputFile:"+pfx+suffix);
		PrintWriter fos = new PrintWriter( new File(pfx+suffix));
		
		
		Set<String> qk = colnames.keySet();
		
		for(String k : qk) {
			List<String> cols = colnames.get(k);
			for(String c : cols) {
				fos.print(c+"\t");
			}
			fos.println();
			fos.println("-----");
			
			List<ArrayList<String>> qr = allq.get(k);
			
			for(ArrayList<String> onerowofcols : qr) {
				for(String v : onerowofcols) 
						fos.print(v+"\t");
				
				fos.println();
			}	
			
			fos.println("=====");
			
		}
		
		fos.flush();
		fos.close();
	}

}
