class Solution {
    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> count = new HashMap<>();
        int maxFreq = 0;
        
        for (List<Integer> row : wall) {
            int prefix = 0;
            // Don't count the last brick edge
            for (int i = 0; i < row.size() - 1; i++) {
                prefix += row.get(i);
                count.put(prefix, count.getOrDefault(prefix, 0) + 1);
                maxFreq = Math.max(maxFreq, count.get(prefix));
            }
        }
        
        return wall.size() - maxFreq;
    }
}