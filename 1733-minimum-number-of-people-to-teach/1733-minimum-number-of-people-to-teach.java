class Solution {
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        int m = languages.length;
        boolean[][] knows = new boolean[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int lang : languages[i]) {
                knows[i + 1][lang] = true;
            }
        }

        // Find users who cannot communicate with at least one friend
        Set<Integer> needTeach = new HashSet<>();
        for (int[] f : friendships) {
            int u = f[0], v = f[1];
            boolean canCommunicate = false;
            for (int lang = 1; lang <= n; lang++) {
                if (knows[u][lang] && knows[v][lang]) {
                    canCommunicate = true;
                    break;
                }
            }
            if (!canCommunicate) {
                needTeach.add(u);
                needTeach.add(v);
            }
        }

        if (needTeach.isEmpty()) return 0;

        // Try teaching each language
        int minTeach = Integer.MAX_VALUE;
        for (int lang = 1; lang <= n; lang++) {
            int count = 0;
            for (int user : needTeach) {
                if (!knows[user][lang]) count++;
            }
            minTeach = Math.min(minTeach, count);
        }

        return minTeach;
    }
}