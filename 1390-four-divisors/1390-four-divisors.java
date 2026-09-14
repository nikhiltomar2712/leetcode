class Solution {
    public int sumFourDivisors(int[] nums) {
        int ans = 0;
        
        for (int num : nums) {
            int sum = 0;
            int count = 0;
            
            for (int i = 1; i * i <= num; i++) {
                if (num % i == 0) {
                    sum += i;
                    count++;
                    
                    if (i != num / i) {
                        sum += num / i;
                        count++;
                    }
                    
                    if (count > 4) break;
                }
            }
            
            if (count == 4) {
                ans += sum;
            }
        }
        
        return ans;
    }
}