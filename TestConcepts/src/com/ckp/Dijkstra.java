// 20230503
package lc;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;


public class Solution {
	
	public int V = 9;
	
	// from the not finalized vertices find the vertices with minimum weight and return its index.
	public int minimumWts(Boolean[] spset, int[] wts) {
		
		int idx = -1;
		int min = Integer.MAX_VALUE;
		
		for(int i=0;i<V;i++) {
			if(!spset[i] && wts[i]<min) {
				min = wts[i];
				idx = i;
			}
		}
		
		return idx;
		
	}
	
	public void dijkstra(int[][] g, int src, int tgt) {
		
		//initialization
		int[] wts = new int[V];
		Arrays.fill(wts, Integer.MAX_VALUE);
		
		Boolean[] spFound = new Boolean[V];
		Arrays.fill(spFound, false);
		
		
		int[] prvV = new int[V]; //to make back track of the shortest path easy.
		Arrays.fill(prvV, Integer.MAX_VALUE);
		
		wts[src] = 0;
		
		
		for (int count = 0; count < V; count++) {

			int u = minimumWts(spFound, wts);
			spFound[u] = true;

			if (u == tgt) break; // if we want to stop for target then we can break here.

			int[] adj = g[u];

			for (int a = 0; a < V; a++)
				if (!spFound[a] && adj[a] != 0 && adj[a] + wts[u] < wts[a]) {
					wts[a] = adj[a] + wts[u];
					prvV[a] = u; // adj vertex is having least weight so set the incoming vertex.
				}
		}
		
//		printWts(wts,prvV);
		printPath(prvV,src,tgt);
		
		
	}
	
	public void printWts(int[] wts, int[] prv) {
		System.out.println("Vertex		 wts");
		for(int i=0;i<V;i++)
			System.out.println(i+"		"+wts[i]);
		
		int[] wtssorted = new int[100];
		Arrays.fill(wtssorted, -1);
		for(int i=0;i<V;i++) 
			wtssorted[wts[i]] = i;
		
		
		for(int i=0;i<100;i++) {
			
			if(-1!=wtssorted[i]) System.out.printf(wtssorted[i]+"("+i+")prv:"+prv[wtssorted[i]]+", ");
		}
		
		
		
	}
	
	public void printPath(int [] prvarr, int src, int tgt) {
		//System.out.println("Track is :");
		//for(int i=0;i<prvarr.length;i++) System.out.printf("{"+i+"-> "+prvarr[i]+"}, ");
		//System.out.println("--");
		int c = tgt;
		String s = ":"+tgt;
		while(true) {
			c = prvarr[c];
			s = c+"->"+s;
			
			if(c==src) break;
		}
		
		System.out.println(s);
		
	}
	
	
	
	
	
	// Driver's code
    public static void main(String[] args)
    {
        //Let us use the example graph
        int graph[][]
            = new int[][] { { 0, 4, 0, 0, 0, 0, 0, 8, 0 },
                            { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
                            { 0, 8, 0, 7, 0, 4, 0, 0, 2 },
                            { 0, 0, 7, 0, 9, 14, 0, 0, 0 },
                            { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
                            { 0, 0, 4, 14, 10, 0, 2, 0, 0 },
                            { 0, 0, 0, 0, 0, 2, 0, 1, 6 },
                            { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
                            { 0, 0, 2, 0, 0, 0, 6, 7, 0 } };
        Solution s = new Solution();
    
        // Function call
        s.dijkstra(graph, 0,2);
    }

}
