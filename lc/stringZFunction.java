/*
It is supposed to be easier to comprehand, idea is to have an array called Z array, where element at index i 
represents the longest suffix starting at i going to right of string s[i] which is also the prefix s[0...n-1].
To search the pattern concatenate the pattern with the target string while keeping one noexistence charcater in
between like
pattern = "abc"
target = "xbnskabcjkkabc"
p%t= "abc%xbnskabcjkkabc"
Now apply Z function on this and it will give all the indexes where the pattern is found.
*/
 public int[] getZ(char[] s){
        int n = s.length;
        int z[]= new int[n];
        if(n==0) return z;
        z[0] = n;
        int l=0, r = 0; 
        for( int i=1; i<n; i++){
          if(i > r){
            	l = r = i;
		while(r < n && s[r-l] == s[r])
			r++;
		z[i] = r-l; 
		r--;
          }else{
                if ( z[i-l] < r - i + 1)
                    z[i] = z[i - l];
                else{
                    l = i;
                    while (r<n && s[r-l] == s[r]) r++;
                    z[i]=r-l;  r--;
                }
             }
        }
        return z;

}// end of getZ
