class Solution {
    public int subarrayBitwiseORs(int[] arr) {
        // Use a set to store all distinct results
        Set<Integer> resultSet = new HashSet<>();
        // Set to store the ORs of subarrays ending at the previous position
        Set<Integer> prev = new HashSet<>();
        
        for (int num : arr) {
            // New set for subarrays ending at current position
            Set<Integer> curr = new HashSet<>();
            // A subarray of just the current element
            curr.add(num);
            
            // Combine current element with all previous subarray ORs
            for (int val : prev) {
                curr.add(val | num);
            }
            
            // Add all results from current position to the global set
            resultSet.addAll(curr);
            // Move to next position
            prev = curr;
        }
        
        return resultSet.size();
    }
}