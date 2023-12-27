public class IntList {
	public int first;
	public IntList rest;


    public static IntList incrList(IntList L, int x) {
        /* Your code here. */
        return dincrList(L, -x);        
    }

    /** Returns an IntList identical to L, but with
      * each element incremented by x. Not allowed to use
      * the 'new' keyword. */
    public static IntList dincrList(IntList L, int x) {
        /* Your code here. */
        int n = L.size();
        IntList p = null;
        for (int i = n - 1; i >= 0; i--){
            p = new IntList(L.get(i) - x, p);
        }
        return p;
    }


	public IntList(int f, IntList r) {
		first = f;
		rest = r;
	}

	/** Return the size of the list using... recursion! */
	public int size() {
        if (rest == null){
            return 1;
        }
		return 1 + rest.size();
	}

	/** Return the size of the list using no recursion! */
	public int iterativeSize() {
        IntList root = this;
        int cnt = 0;
        while (root != null){
            cnt++;
            root = root.rest;
        }
		return cnt;
	}

	/** Returns the ith value in this list.*/
	public int get(int i) {
        if (i == 0){
            return first;
        }
        if (rest == null){
            return -1;
        }
		return rest.get(i - 1);
	}

	public static void main(String[] args) {
		IntList L = new IntList(15, null);
		L = new IntList(10, L);
		L = new IntList(5, L);

        System.out.println(L.get(1));
        System.out.println(incrList(L, 3));
        System.out.println(dincrList(L, 3)); 
	}
} 