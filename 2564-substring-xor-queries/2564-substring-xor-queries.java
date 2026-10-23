class Solution {
    public int[][] substringXorQueries(String s, int[][] queries) {
        Map<Integer, int[]> map = new HashMap<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') {
                map.putIfAbsent(0, new int[]{i, i});
            } else {
                int val = 0;
                for (int j = i; j < n && j - i < 31; j++) {
                    val = (val << 1) | (s.charAt(j) - '0');
                    map.putIfAbsent(val, new int[]{i, j});
                }
            }
        }
        int[][] res = new int[queries.length][];
        for (int i = 0; i < queries.length; i++) {
            int target = queries[i][0] ^ queries[i][1];
            res[i] = map.getOrDefault(target, new int[]{-1, -1});
        }
        return res;
    }
}