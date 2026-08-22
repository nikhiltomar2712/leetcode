class Solution {
    public boolean canReorderDoubled(int[] arr) {
        // Count frequency
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : arr) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        
        // Sort the unique numbers by absolute value
        Integer[] keys = count.keySet().toArray(new Integer[0]);
        Arrays.sort(keys, Comparator.comparingInt(Math::abs));
        
        for (int num : keys) {
            if (count.get(num) == 0) continue;
            
            int target = num * 2;
            
            // Not enough doubles available
            if (count.getOrDefault(target, 0) < count.get(num)) {
                return false;
            }
            
            // Consume the pairs
            count.put(target, count.get(target) - count.get(num));
        }
        
        return true;
    }
}