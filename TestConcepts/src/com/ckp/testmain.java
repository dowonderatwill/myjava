package com.ckp;


import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import java.util.TreeSet;

public class testmain {
	
	
	public static void main(String[] args) {
		
		Instant ref = Instant.now();
				
		int SIZE = 90_000; 
//		int SIZE = 10; 
		
		System.out.println("Size of Node:"+SIZE);
		Random rd = new Random();
		int[] a = new int[SIZE];
		TreeSet<Integer> tset = new TreeSet<Integer>();
		
		ref = Instant.now();
		while(tset.size() < SIZE) {
			tset.add(100+rd.nextInt());
		}
		
		LogTime.printTime(ref, "TimeInFillTreeSet");
		
		Iterator iter = tset.iterator();
		for(int i =0;i<SIZE;i++) a[i] = (int)iter.next();
		
		ref = Instant.now();
		Node r = Node.buildTree(a);
		LogTime.printTime(ref, "TimeInBuildBST");
		
//		LogTime.printTree("", r);
		System.out.println("------------");
		
//		Node.inorderWalk(r);
		counter clh = new testmain(). new counter(); 
		counter crh = new testmain(). new counter(); 
		int lh=0,rh=0;
		Node.findLeftHeight(r.l,lh,clh);Node.findRightHeight(r.l,rh,crh);
		int LEFTHEIGHT = Math.max(clh.getC(),crh.getC());
		
		clh.setC(0);crh.setC(0);lh=0;rh=0;
		Node.findLeftHeight(r.r,lh,clh);Node.findRightHeight(r.r,rh,crh);
		int RIGHTHEIGHT = Math.max(clh.getC(),crh.getC());
		
		System.out.println("LeftHeight->" +LEFTHEIGHT+" RightHeight->" +RIGHTHEIGHT );
		ref = Instant.now();
		System.out.println(Node.get(a[SIZE-2], r));
		LogTime.printTime(ref, "time for get");
	}
	
	static class Node{
		
		int k;
		Node p,l,r;
		

		/**
		 * This will build the binary tree and return the root node.
		 * @return Node: the root node.
		 */
		
		static Node buildTree(int [] a) {
			int sz = a.length; int mid = sz/2; int rv= a[mid]; Arrays.sort(a);
			Node r = new Node(); r.k = rv; r.p = null;
			
			for(int i = mid; i<sz-1;i++) a[i] = a[i+1]; // resize by removing the root node value.
			sz--; mid--;
			
			// traverse all element from root to the left
			for(int i = mid - 1 ; i >=0; i-=2) Node.insert(r, a[i]); // first jump by 2 to get lower values
			for(int i = mid ; i >=0; i-=2) Node.insert(r, a[i]); // then jump to get other values.
			
			
			// traverse all element from root to the right
			for(int i = sz - 1 ; i > mid ; i-=2) Node.insert(r, a[i]);
			for(int i = sz - 2 ; i > mid ; i-=2) Node.insert(r, a[i]);
			
			return r; // the node is built now return the root node.
		}
		
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
		
		static void inorderWalk(Node r) {
			if(null != r ) {
				inorderWalk(r.l);
				System.out.printf(" %d ",r.k);
				inorderWalk(r.r);
			}
		}
	
		static void findLeftHeight(Node rt, int lh, counter c ) {
			
			while(null != rt.l ) {
				lh ++;
				rt = rt.l;
			}
			c.setC(lh);
		}
		
		static void findRightHeight(Node rt, int rh, counter c ) {
			while(null != rt.r) {
				rh ++;
				rt = rt.r;
			}
			c.setC(rh);
		}
	}
	
	class counter{
		
		int c = 0;

		public int getC() {
			return c;
		}

		public void setC(int c) {
			this.c = c;
		}
		
		
	}
	
	
	static class LogTime{
		
		static void printTime( Instant ref, String m) {
			Duration d = Duration.between(ref,Instant.now());
			System.out.printf(m+" [ Elapsed %d ms (%d ns) ]\r\n",d.toMillis(), d.toNanos());
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
