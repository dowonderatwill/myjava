import java.util.Arrays;
import java.util.List;

public class BinarySearch {
	
	public int searchLowestIndexGreaterOrEqual(int target, List<Integer> list) {
		int idx = -1;
		
		int l=0;
		int r=list.size()-1;
				
		while(l<=r) {
			
			int mid = (l+r) / 2;
			int sv = list.get(mid);
			
			if(sv<target) {
				l = mid + 1;
			}else {
				idx = mid;
				r = mid - 1;
			}
			
		}
		
		return idx;
	}
	
	public int searchHighestIndexLessOrEqual(int target, List<Integer> list) {
		int idx = -1;
		
		int l=0;
		int r=list.size()-1;
				
		while(l<=r) {
		
			int mid = (l+r) / 2;
			int sv = list.get(mid);
			
			if(sv>target) {
				r = mid - 1;
			}else {
				idx = mid;
				l = mid + 1;
			}
			
		}
		
		return idx;
	}
	
	public static void main(String[] args) {
		
		int[] arr = new int[] {0,0,1,1,1,1,2,2,2,4};
		List<Integer> list = Arrays.stream(arr).boxed().toList();
		
		BinarySearch o = new BinarySearch();
		int x = -999;
		/*
		x = o.searchLowestIndexGreaterOrEqual(1, list); System.out.println("x= "+x);
		x = o.searchLowestIndexGreaterOrEqual(-1, list); System.out.println("x= "+x);
		x = o.searchLowestIndexGreaterOrEqual(0, list); System.out.println("x= "+x);
		x = o.searchLowestIndexGreaterOrEqual(2, list); System.out.println("x= "+x);
		x = o.searchLowestIndexGreaterOrEqual(4, list); System.out.println("x= "+x);
		x = o.searchLowestIndexGreaterOrEqual(10, list); System.out.println("x= "+x);
		
//		x = o.searchHighestIndexLessOrEqual(-1, list); System.out.println("x= "+x);
//		x = o.searchHighestIndexLessOrEqual(0, list); System.out.println("x= "+x);
		x = o.searchHighestIndexLessOrEqual(2, list); System.out.println("x= "+x);
		x = o.searchHighestIndexLessOrEqual(4, list); System.out.println("x= "+x);
		x = o.searchHighestIndexLessOrEqual(10, list); System.out.println("x= "+x);
		*/
		
		
		int[] arr2 = new int[] {95,105};
		list = Arrays.stream(arr2).boxed().toList();
		x = o.searchLowestIndexGreaterOrEqual(100, list); System.out.println("x= "+x);
		x = o.searchHighestIndexLessOrEqual(100, list); System.out.println("x= "+x);
	}

}
