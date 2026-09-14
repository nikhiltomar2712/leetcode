class Solution {
    private Map<Integer, Integer> memo = new HashMap<>();
    
    public int getKth(int lo, int hi, int k) {
        List<int[]> list = new ArrayList<>();  // {number, power}
        
        for (int i = lo; i <= hi; i++) {
            list.add(new int[]{i, getPower(i)});
        }
        
        // Sort by power ascending, then by number ascending
        list.sort((a, b) -> {
            if (a[1] != b[1]) return a[1] - b[1];
            return a[0] - b[0];
        });
        
        return list.get(k - 1)[0];
    }
    
    private int getPower(int x) {
        if (x == 1) return 0;
        if (memo.containsKey(x)) return memo.get(x);
        
        int steps;
        if (x % 2 == 0) {
            steps = 1 + getPower(x / 2);
        } else {
            steps = 1 + getPower(3 * x + 1);
        }
        
        memo.put(x, steps);
        return steps;
    }
}