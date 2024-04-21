
1. Unset the right most bit if it is 1 change it to 0.
int t = k;
t = t & t - 1;
System.out.println("2:"+Integer.toBinaryString(t));


2. Get the number pick the right most bit and all 0 thereafter

  int t = k;
	t = t & -t ;
	System.out.println("2:"+Integer.toBinaryString(t));
Example:
1:10100
2:100

3. In a number unset the highestSignificant bit and then get the remaining number
    k= is the input number    ;
    int t = Integer.highestOneBit(k); // get the highest bit, as a value.
		int r = k;
		r&= ~(t);  // unset the highest bit position.
  
