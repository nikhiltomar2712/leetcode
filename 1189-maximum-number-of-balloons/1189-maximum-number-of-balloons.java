class Solution {
    public int maxNumberOfBalloons(String text) {
        int[] count = new int[26];
        for (char c : text.toCharArray()) {
            count[c - 'a']++;
        }
        
        // Divide l and o by 2 because we need two of each
        count['l' - 'a'] /= 2;
        count['o' - 'a'] /= 2;
        
        int ans = Integer.MAX_VALUE;
        for (char c : "balon".toCharArray()) {
            ans = Math.min(ans, count[c - 'a']);
        }
        return ans;
    }
}