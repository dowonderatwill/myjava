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
