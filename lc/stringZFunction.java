
 public int[] getZ(char[] s){
        int n = s.length;
        int z[]= new int[n];
        if(n==0) return z;
        z[0] = n;
        int l=0, r = 0; 
        for( int i=1; i<n; i++){
          if(i > r){
            l = r = i;
		while(r < n && s[r-l] == s[r])r++;
			z[i] = r-l; r--;
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
