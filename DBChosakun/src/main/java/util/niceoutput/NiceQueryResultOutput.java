package util.niceoutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import queries.Queries;

public abstract class NiceQueryResultOutput {
	
	protected HashMap<String,List<ArrayList<String>>> allq = null;
	protected HashMap<String,List<String>> colnames = null;
	
	protected abstract String getSuffix() ;
	protected abstract String getTableHeaderSeparator() ;
	protected abstract String getColSeparatorBegin() ;
	protected abstract String getColSeparatorEnd() ;
	protected abstract String getRowSeparatorBegin() ;
	protected abstract String getRowSeparatorEnd() ;
	protected abstract String getQueryEndSeparator() ;
	protected abstract String getHeader() ;
	protected abstract String getFooter() ;
	
	protected void setQueries(Queries q) {
		this.allq = q.getAllQueryResults();
		this.colnames = q.getAllQueryColumnNames();
		
	}

	
	public void saveResultInAFile(String pfx) throws FileNotFoundException {
		
        String suffix = getSuffix();
        System.out.println("OutputFile:"+pfx+suffix);
		PrintWriter fos = new PrintWriter( new File(pfx+suffix));
		
		
		Set<String> qk = colnames.keySet();
		String cb = getColSeparatorBegin();
		String ce = getColSeparatorEnd();
		String qel = getQueryEndSeparator();  
		String ths = getTableHeaderSeparator();
		String rb = getRowSeparatorBegin();
		String re = getRowSeparatorEnd();
		String hdr = getHeader();
		String ftr = getFooter();
		
		
		fos.println(hdr);
		for(String k : qk) {
			
			fos.println(rb);fos.println(cb);
			fos.println(k);
			fos.println(ce);fos.println(re);
			
			List<String> cols = colnames.get(k);
			fos.println(rb);
			for(String c : cols) {
				fos.print(cb + c + ce);
			}
			fos.println(re);
			fos.println();
			if(null!=ths)fos.println(ths);
			
			List<ArrayList<String>> qr = allq.get(k);
			
			for(ArrayList<String> onerowofcols : qr) {
				fos.println(rb);
				for(String v : onerowofcols) 
						fos.print(cb + v + ce);
				fos.println(re);
				fos.println();
			}	
			
			if(null!=qel)fos.println(qel);
			
		}
		fos.println(ftr);
		fos.flush();
		fos.close();
	}
	                        

}
