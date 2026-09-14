class Solution {
    public int findLucky(int[] arr) {
        // Frequency map: value -> count
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : arr) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        
        int result = -1;
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            if (entry.getKey().equals(entry.getValue())) {
                result = Math.max(result, entry.getKey());
            }
        }
        return result;
    }
}