class Solution {
    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        List<Integer> result = new ArrayList<>(n);
        
        // Coordinate compression
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        
        // Map values to compressed indices (1-based for BIT)
        Map<Integer, Integer> rank = new HashMap<>();
        for (int i = 0; i < sorted.length; i++) {
            rank.put(sorted[i], i + 1);
        }
        
        // Fenwick Tree
        BIT bit = new BIT(n);
        
        // Process from right to left
        for (int i = n - 1; i >= 0; i--) {
            int idx = rank.get(nums[i]);
            // Query count of elements smaller than current
            result.add(bit.query(idx - 1));
            // Add current element to BIT
            bit.update(idx, 1);
        }
        
        Collections.reverse(result);
        return result;
    }
    
    class BIT {
        int[] tree;
        int n;
        
        BIT(int n) {
            this.n = n;
            tree = new int[n + 1];
        }
        
        void update(int idx, int val) {
            while (idx <= n) {
                tree[idx] += val;
                idx += idx & -idx;
            }
        }
        
        int query(int idx) {
            int sum = 0;
            while (idx > 0) {
                sum += tree[idx];
                idx -= idx & -idx;
            }
            return sum;
        }
    }
}