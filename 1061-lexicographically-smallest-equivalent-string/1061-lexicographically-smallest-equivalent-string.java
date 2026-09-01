class Solution {
    private int[] parent = new int[26];

    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        // Initialize each character as its own parent
        for (int i = 0; i < 26; i++) {
            parent[i] = i;
        }

        // Union the equivalent characters, always keeping the smaller one as parent
        for (int i = 0; i < s1.length(); i++) {
            int x = s1.charAt(i) - 'a';
            int y = s2.charAt(i) - 'a';
            union(x, y);
        }

        // Build the result by mapping each character in baseStr to its smallest equivalent
        StringBuilder sb = new StringBuilder();
        for (char c : baseStr.toCharArray()) {
            sb.append((char) ('a' + find(c - 'a')));
        }
        return sb.toString();
    }

    private void union(int x, int y) {
        int px = find(x);
        int py = find(y);
        if (px == py) return;
        
        // Always attach the larger root to the smaller one
        if (px < py) {
            parent[py] = px;
        } else {
            parent[px] = py;
        }
    }

    private int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // path compression
        }
        return parent[x];
    }
}