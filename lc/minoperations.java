	public int dp[][] ;
	
	public static void main(String[] args) {
	
		System.out.println("BIN:");

	} 
	
	
	public int minimumOperations(int[][] grid) {
		
		int ans = Integer.MAX_VALUE;
		int csz = grid[0].length;
		dp = new int[csz][10];
		for(int i=0;i<=9;i++) {
			ans = Math.min(ans,  solve(0,i,grid));
		}
        
		return ans;
    }

	
	public int solve(int j, int d, int[][] grid) {

		int ans = Integer.MAX_VALUE;
		int rsz = grid.length;

		if (j >= grid[0].length)
			return 0;

		if (dp[j][d] > 0)
			return dp[j][d];
		
		int tc = 0;
		
		for (int i = 0; i < rsz; i++)
			if (d != grid[i][j])
				tc++;

		for (int k = 0; k <= 9; k++) {
			if (k != d)
				ans = Math.min(ans, tc + solve(j + 1, k, grid));
		}

		return dp[j][d] = ans;
	}
	
