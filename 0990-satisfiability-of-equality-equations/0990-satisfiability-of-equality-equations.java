class Solution {
    private int[] parent = new int[26];

    public boolean equationsPossible(String[] equations) {
        // Initialize each variable as its own parent
        for (int i = 0; i < 26; i++) {
            parent[i] = i;
        }

        // First pass: process all equality equations (==)
        for (String eq : equations) {
            if (eq.charAt(1) == '=') {
                int x = eq.charAt(0) - 'a';
                int y = eq.charAt(3) - 'a';
                union(x, y);
            }
        }

        // Second pass: check inequality equations (!=)
        for (String eq : equations) {
            if (eq.charAt(1) == '!') {
                int x = eq.charAt(0) - 'a';
                int y = eq.charAt(3) - 'a';
                if (find(x) == find(y)) {
                    return false; // Contradiction found
                }
            }
        }

        return true;
    }

    private int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // Path compression
        }
        return parent[x];
    }

    private void union(int x, int y) {
        parent[find(x)] = find(y);
    }
}