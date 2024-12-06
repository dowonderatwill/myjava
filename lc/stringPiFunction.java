// An efficient algorithm for searching occurrences of a pattern within a text.
// Compute the array using Pi function,  
//an array that represents the length of the longest proper prefix which is also a suffix for each prefix of a given string.
/*
The prefix function for a string s is an array π, 
where π[i] is the length of the longest proper prefix of the substring s[0...i]
which is also a suffix of this substring. 
Proper prefix of a string is a prefix that is not equal to the string itself.
It is obvious that π[0]=0 because a string of length 1 has no proper prefixes
*/

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
