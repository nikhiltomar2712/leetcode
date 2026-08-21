class Solution {
    public int[] beautifulArray(int n) {
        List<Integer> res = new ArrayList<>();
        res.add(1);
        
        while (res.size() < n) {
            List<Integer> temp = new ArrayList<>();
            
            // Odd numbers first: 2*x - 1
            for (int x : res) {
                if (2 * x - 1 <= n) {
                    temp.add(2 * x - 1);
                }
            }
            
            // Even numbers: 2*x
            for (int x : res) {
                if (2 * x <= n) {
                    temp.add(2 * x);
                }
            }
            
            res = temp;
        }
        
        // Convert List to array
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }
}