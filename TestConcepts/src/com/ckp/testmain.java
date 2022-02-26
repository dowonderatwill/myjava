package com.ckp;


import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Random;

public class testmain {
	
	public static void main(String[] args) {
		
		Instant ref = Instant.now();
				
		int SIZE = 14; 
		
		Random rd = new Random();
		int[] a = new int[SIZE];
		
		for(int i = 0; i < SIZE; i++) {	a[i] = 100+rd.nextInt(1000);}
		
		a = new int[]{257,269,353,366,452,473,487,583,702,705,945,1037,1073,1081};
		
		ref = Instant.now();
		Arrays.sort(a);	LogTime.printTime(ref, "sort time");
		
		int midpos = SIZE/2 ;
		Node r = new Node();
		r.p = null;
		r.k = a[midpos];
//		System.out.println("root node:" + r.k+ " pos:" +midpos);
//		for(int i = 0;i<SIZE; i++) System.out.printf("%d,",a[i]); 
//		System.out.println();
		
		// resize the array item by removing the root item.
		for (int i = midpos;i < SIZE - 1;i++) a[i] = a[i+1];
		
		SIZE--; midpos--;
		
//		for(int i = 0;i<SIZE; i++) System.out.printf("%d,",a[i]); 
//		System.out.println();
		
		
		// traverse all element from root to the left
		for(int i = midpos - 1 ; i >=0; i-=2) Node.insert(r, a[i]);
		for(int i = midpos ; i >=0; i-=2) Node.insert(r, a[i]);
		
		
		// traverse all element from root to the right
		for(int i = SIZE - 1 ; i > midpos ; i-=2) Node.insert(r, a[i]);
		for(int i = SIZE - 2 ; i > midpos ; i-=2) Node.insert(r, a[i]);
				
		LogTime.printTree("", r);
		System.out.println("------------");
		
		Node.insert(r, 116);
		LogTime.printTree("", r);
		
		System.out.println(" Search result : " + Node.get(366,r));
	}
	
	static class Node{
		
		int k;
		Node p,l,r;
		
		static void insert(Node t, int v) {
			
			if (v < t.k) {
				
				if(t.l == null){
					Node n = new Node();
					n.k = v;
					t.l = n;
					n.p = t;
					return;
				}else {
					insert(t.l, v);
				}
				
			}else {
				if(t.r == null) {
					Node n = new Node();
					n.k = v;
					t.r = n;
					n.p = t;
					return;
				}else {
					insert(t.r,v);
				}
				
			}
			
		}
		static int get(int k, Node n) {
			int i = -1;

			if(k == n.k) {
				return 1;
			}
			
			if ( k < n.k) {
				if(null != n.l) {
					
					i = get(k,n.l);
				}
			}else {
				if(null != n.r) {
					
					i = get(k,n.r);
				}
			}
			
			return i;
		}
		
	}
	
	
	static class LogTime{
		
		static void printTime( Instant ref, String m) {
			System.out.printf(m+" [ Elapsed %d ms ]\r\n", Duration.between(ref,Instant.now()).toMillis());
		}
		
		static void printTree(String pfx, Node n) {
			if(null != n) {
				printTree(pfx+ "   ",n.r);
				System.out.println(pfx+"|--"+n.k);
				printTree(pfx+ "   ",n.l);
			}
		}
		
	}
}
