public static int[] getPi(char[] s) {
	int sz = s.length;
	int[] pi = new int[sz];
	int q = 1;
	int k = 0;
	pi[0] = 0;

	for (q = 1; q < sz; q++) {

		while (k > 0 && s[k] != s[q])
			k = pi[k - 1];

		if (s[k] == s[q])
			k++;
		
		pi[q] = k;
	}

	return pi;
}

/* Using the above pi function to find the pattern in the target string */
// KMP-algorithm 2nd step. Get all the indexes where pattern is found
public List<Integer> getFoundIndexes(String t, String p){
        int n = t.length();
        int m = p.length();
        List<Integer> res = new ArrayList<>();
        int[] pi = getPreprocessedOfPattern(p);
        int k = 0;
        
        for(int i=0;i<n;i++){
            
            while(k>0 && t.charAt(i) != p.charAt(k) )
                k = pi[k-1];
        
            if(t.charAt(i) == p.charAt(k))
                k++;
            
            if(k==m) {
                res.add(i-m+1);
                k = pi[k-1];
            }
            
        }
        return res;
}
