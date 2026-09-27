class Solution {
    public int minCharacters(String a, String b) {
        int[] ca = new int[26];
        int[] cb = new int[26];
        for (char c : a.toCharArray()) ca[c - 'a']++;
        for (char c : b.toCharArray()) cb[c - 'a']++;

        int n = a.length(), m = b.length();
        int ans = Integer.MAX_VALUE;

        // Condition 3: all characters equal
        for (int i = 0; i < 26; i++) {
            ans = Math.min(ans, (n - ca[i]) + (m - cb[i]));
        }

        // Conditions 1 & 2: strict separation at split i
        // a's chars <= i, b's chars > i  (and vice versa)
        for (int i = 0; i < 25; i++) {
            // make max(a) <= i  ->  b's chars > i
            int costA = 0, costB = 0;
            for (int j = i + 1; j < 26; j++) costA += ca[j]; // a chars above i
            for (int j = 0; j <= i; j++) costB += cb[j];     // b chars at or below i
            ans = Math.min(ans, costA + costB);

            // symmetric: make max(b) <= i  ->  a's chars > i
            int costA2 = 0, costB2 = 0;
            for (int j = 0; j <= i; j++) costA2 += ca[j];
            for (int j = i + 1; j < 26; j++) costB2 += cb[j];
            ans = Math.min(ans, costA2 + costB2);
        }

        return ans;
    }
}