package com.ckp;

import java.util.Random;

public class testrecursion {
	
	public static void main(String[] args) {
		System.out.println("Begin");
		work.init();
		int i = 0;
		work.workRecursive(i);
		System.out.println("End");
	}
	
	static class work{
		static int SIZE = 10;
		static int[] a = new int[SIZE];
		static Random r = new Random();
		static void init() {
			for(int i =0;i<SIZE;i++) a[i] = 100 + r.nextInt(1000);  
		}
		
		static int c = 0;
		
		static void workRecursive(int idx) {
			if(idx < SIZE - 1) {
				idx ++;
				System.out.print(a[idx]+"  idx="+idx+" , "); 
				workRecursive(idx);
				System.out.println("\r\n call..." + idx+ " :" + a[idx]);
			}else return;
		}
	}

}
