import java.util.HashSet;
import java.util.Set;

class Solution {
    public boolean checkIfExist(int[] arr) {
        Set<Integer> seen = new HashSet<>();
        
        for (int num : arr) {
            // Check if double exists in seen
            if (seen.contains(2 * num)) {
                return true;
            }
            // Check if half exists in seen (only if num is even)
            if (num % 2 == 0 && seen.contains(num / 2)) {
                return true;
            }
            seen.add(num);
        }
        
        return false;
    }
}