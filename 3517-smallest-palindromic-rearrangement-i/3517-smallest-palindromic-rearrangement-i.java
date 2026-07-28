class Solution {
    public String smallestPalindrome(String s) {
        int n = s.length();
        // Extract and sort the left half
        char[] half = s.substring(0, n / 2).toCharArray();
        Arrays.sort(half);
        String sortedHalf = new String(half);
        
        // Build result: sorted left + (middle if odd) + reverse of sorted left
        StringBuilder sb = new StringBuilder(sortedHalf);
        if (n % 2 == 1) {
            sb.append(s.charAt(n / 2));
        }
        sb.append(new StringBuilder(sortedHalf).reverse());
        return sb.toString();
    }
}