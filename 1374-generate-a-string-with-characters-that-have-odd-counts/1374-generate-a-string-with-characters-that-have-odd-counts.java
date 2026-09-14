class Solution {
    public String generateTheString(int n) {
        StringBuilder sb = new StringBuilder();
        
        if (n % 2 == 1) {
            // n is odd → all 'a's
            for (int i = 0; i < n; i++) {
                sb.append('a');
            }
        } else {
            // n is even → (n-1) 'a's + one 'b'
            for (int i = 0; i < n - 1; i++) {
                sb.append('a');
            }
            sb.append('b');
        }
        
        return sb.toString();
    }
}