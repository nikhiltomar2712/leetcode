class Solution {
    public boolean matchReplacement(String s, String sub, char[][] mappings) {
        boolean[][] map = new boolean[128][128];
        for (char[] m : mappings) {
            map[m[0]][m[1]] = true;
        }
        int n = s.length(), m = sub.length();
        for (int i = 0; i + m <= n; i++) {
            boolean ok = true;
            for (int j = 0; j < m; j++) {
                char sc = s.charAt(i + j);
                char subc = sub.charAt(j);
                if (sc != subc && !map[subc][sc]) {
                    ok = false;
                    break;
                }
            }
            if (ok) return true;
        }
        return false;
    }
}