class Solution {
    public String digitSum(String s, int k) {
        while (s.length() > k) {
            StringBuilder sb = new StringBuilder();
            int n = s.length();
            
            // Iterate through the string in chunks of size k
            for (int i = 0; i < n; i += k) {
                int sum = 0;
                // Compute the sum of digits for the current group
                for (int j = i; j < Math.min(i + k, n); j++) {
                    sum += s.charAt(j) - '0';
                }
                sb.append(sum);
            }
            // Update s for the next round
            s = sb.toString();
        }
        return s;
    }
}