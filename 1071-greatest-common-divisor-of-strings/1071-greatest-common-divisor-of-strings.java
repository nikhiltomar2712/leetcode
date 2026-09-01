class Solution {
    public String gcdOfStrings(String str1, String str2) {
        // If str1 + str2 != str2 + str1, no common divisor exists
        if (!(str1 + str2).equals(str2 + str1)) {
            return "";
        }
        
        // The length of the GCD string is the GCD of the two lengths
        int gcdLength = gcd(str1.length(), str2.length());
        return str1.substring(0, gcdLength);
    }
    
    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}