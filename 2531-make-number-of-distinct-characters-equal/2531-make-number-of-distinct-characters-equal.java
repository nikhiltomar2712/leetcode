class Solution {
    public boolean isItPossible(String word1, String word2) {
        int[] c1 = new int[26];
        int[] c2 = new int[26];
        for (char c : word1.toCharArray()) c1[c - 'a']++;
        for (char c : word2.toCharArray()) c2[c - 'a']++;
        int d1 = 0, d2 = 0;
        for (int i = 0; i < 26; i++) {
            if (c1[i] > 0) d1++;
            if (c2[i] > 0) d2++;
        }
        for (int i = 0; i < 26; i++) {
            if (c1[i] == 0) continue;
            for (int j = 0; j < 26; j++) {
                if (c2[j] == 0) continue;
                int nd1 = d1, nd2 = d2;
                if (i != j) {
                    if (c1[i] == 1) nd1--;
                    if (c1[j] == 0) nd1++;
                    if (c2[j] == 1) nd2--;
                    if (c2[i] == 0) nd2++;
                }
                if (nd1 == nd2) return true;
            }
        }
        return false;
    }
}