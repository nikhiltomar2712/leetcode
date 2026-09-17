class Solution {
    public int kthSmallest(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;

        int[] sums = mat[0].clone();

        for (int i = 1; i < m; i++) {
            PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
            for (int s : sums) {
                for (int j = 0; j < n; j++) {
                    int val = s + mat[i][j];
                    if (pq.size() < k) {
                        pq.offer(val);
                    } else if (val < pq.peek()) {
                        pq.poll();
                        pq.offer(val);
                    } else {
                        break;
                    }
                }
            }
            sums = new int[pq.size()];
            int idx = 0;
            while (!pq.isEmpty()) {
                sums[idx++] = pq.poll();
            }
            Arrays.sort(sums);
        }

        return sums[k - 1];
    }
}