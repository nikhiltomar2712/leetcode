import java.util.HashMap;
import java.util.Map;

class Solution {
    public int findLatestStep(int[] arr, int m) {
        int n = arr.length;
        if (m == n) return n; // whole array becomes one group at the last step
        
        int[] length = new int[n + 2]; // length[i] = size of group at boundary i
        int[] count = new int[n + 1];  // count[s] = number of groups of size s
        int ans = -1;
        
        for (int step = 0; step < n; step++) {
            int pos = arr[step];
            int left = length[pos - 1];
            int right = length[pos + 1];
            int newLen = left + right + 1;
            
            // Remove old groups from count
            if (left > 0) count[left]--;
            if (right > 0) count[right]--;
            
            // Add new group
            count[newLen]++;
            
            // Update boundaries
            length[pos - left] = newLen;
            length[pos + right] = newLen;
            length[pos] = newLen; // optional, helps for single checks
            
            if (count[m] > 0) {
                ans = step + 1;
            }
        }
        
        return ans;
    }
}