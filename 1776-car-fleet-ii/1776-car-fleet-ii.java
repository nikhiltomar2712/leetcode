class Solution {
    public double[] getCollisionTimes(int[][] cars) {
        int n = cars.length;
        double[] res = new double[n];
        Arrays.fill(res, -1.0);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = n - 1; i >= 0; i--) {
            int pos = cars[i][0], speed = cars[i][1];
            while (!stack.isEmpty()) {
                int j = stack.peek();
                int posJ = cars[j][0], speedJ = cars[j][1];
                if (speed <= speedJ) {
                    stack.pop();
                } else {
                    double time = (double) (posJ - pos) / (speed - speedJ);
                    if (res[j] != -1.0 && time >= res[j]) {
                        stack.pop();
                    } else {
                        res[i] = time;
                        break;
                    }
                }
            }
            stack.push(i);
        }
        return res;
    }
}