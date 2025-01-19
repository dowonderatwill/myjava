
The PseudoCode

![IMG_6900](https://github.com/user-attachments/assets/8250b1cd-ba0f-473e-96d2-ed6a463e7483)


```
public class Sparse {
	
	int[][] spds ;
	
	
	public Sparse(int arr []) {
		
		int N = arr.length;
		int k = log2(N);
		
		spds = new int[N][k+1];
		
		for(int i=0;i<N;i++) spds[i][0] = arr[i];

		for (int j = 1; 1 << j <= N; j++) {
			for (int i = 0; i + (1 << j) - 1 < N; i++) 
				spds[i][j] = Math.min(spds[i][j - 1], spds[i + (1 << (j - 1))][j - 1]);
		}
	}
	
	public int query(int L, int R) {
		int N = R - L + 1;
		int k = log2(N);
		
		int ans1 = spds[L][k];
		int ans2 = spds[R - (1<<k) +  1][k];
		return Math.min(ans1, ans2);
	}
	
	
	public int log2(int n) {
		return (int) (Math.log(n) / Math.log(2));
	}
	
	public static void main(String[] args) {
		Sparse s = new Sparse(new int[] {7, 2, 3, 0, 5, 10, 3, 12, 18});
		
		System.out.println(s.query(0, 4)); 
        System.out.println(s.query(4, 7)); 
        System.out.println(s.query(7, 8));
	}

}
```
