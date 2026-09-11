class Solution {
    public int totalNumbers(int[] digits) {
        Set<Integer> uniqueNumbers = new HashSet<>();
        int n = digits.length;
        
        // Try all combinations of 3 positions
        for (int i = 0; i < n; i++) {
            // Hundreds place - cannot be 0
            if (digits[i] == 0) continue;
            
            for (int j = 0; j < n; j++) {
                if (j == i) continue; // Can't reuse same index
                
                for (int k = 0; k < n; k++) {
                    if (k == i || k == j) continue; // Can't reuse same index
                    
                    // Ones place must be even
                    if (digits[k] % 2 != 0) continue;
                    
                    int num = digits[i] * 100 + digits[j] * 10 + digits[k];
                    uniqueNumbers.add(num);
                }
            }
        }
        
        return uniqueNumbers.size();
    }
}