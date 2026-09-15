class Solution {
    public int[] processQueries(int[] queries, int m) {
        List<Integer> p = new ArrayList<>();
        for (int i = 1; i <= m; i++) {
            p.add(i);
        }

        int[] result = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int q = queries[i];
            int pos = p.indexOf(q);
            result[i] = pos;

            // move q to the front
            p.remove(pos);
            p.add(0, q);
        }

        return result;
    }
}