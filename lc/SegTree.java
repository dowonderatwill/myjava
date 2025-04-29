public class SegTree {
	int[] t;

	public void build(int[] a, int i, int l, int r) {

		if (l == r) {
			t[i] = a[i];

		} else {

			int mid = (l + r) / 2;

			build(a, 2 * i, l, mid);
			build(a, 2 * i + 1, mid + 1, r);

			t[i] = Math.max(t[2 * i], t[2 * i + 1]);
		}
	}

	
	public int searchAndUpdate(int n, int st, int en, int k) {
		if(t[n]<k) return -1;
		
		if(st==en) return st;
		
		int mid = (st+en)/2;
		
		int pos = -1;
		
		if(t[2*n]>k)
			pos =  searchAndUpdate(2*n, st, mid, k);
		else 
			pos =  searchAndUpdate(2*n+1,mid+1, en, k);
		
		t[n] = Math.max(t[2*n], t[2*n+1]);
		
		return pos;
	}
}
