class Solution {
    // map[left][right] = bitmask of possible top blocks
    private int[][] map = new int[7][7];
    private Map<String, Boolean> memo = new HashMap<>();

    public boolean pyramidTransition(String bottom, List<String> allowed) {
        for (String s : allowed) {
            int a = s.charAt(0) - 'A';
            int b = s.charAt(1) - 'A';
            int c = s.charAt(2) - 'A';
            map[a][b] |= (1 << c);
        }
        return dfs(bottom);
    }

    private boolean dfs(String row) {
        if (row.length() == 1) return true;
        if (memo.containsKey(row)) return memo.get(row);

        // Generate all possible next rows
        List<String> nextRows = new ArrayList<>();
        buildNext(row, 0, new StringBuilder(), nextRows);

        for (String next : nextRows) {
            if (dfs(next)) {
                memo.put(row, true);
                return true;
            }
        }
        memo.put(row, false);
        return false;
    }

    private void buildNext(String row, int i, StringBuilder path, List<String> res) {
        if (i == row.length() - 1) {
            res.add(path.toString());
            return;
        }
        int a = row.charAt(i) - 'A';
        int b = row.charAt(i + 1) - 'A';
        int mask = map[a][b];
        for (int c = 0; c < 7; c++) {
            if ((mask & (1 << c)) != 0) {
                path.append((char) ('A' + c));
                buildNext(row, i + 1, path, res);
                path.deleteCharAt(path.length() - 1);
            }
        }
    }
}