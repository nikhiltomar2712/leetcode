class Solution {
    public int[] recoverArray(int n, int[] sums) {
        Arrays.sort(sums);

        List<Integer> sumsList = new ArrayList<>();
        for (int x : sums) {
            sumsList.add(x);
        }

        List<Integer> ans = new ArrayList<>();

        while (ans.size() < n) {
            int diff = sumsList.get(1) - sumsList.get(0);

            Map<Integer, Integer> count = new HashMap<>();
            for (int x : sumsList) {
                count.put(x, count.getOrDefault(x, 0) + 1);
            }

            List<Integer> with = new ArrayList<>();
            List<Integer> without = new ArrayList<>();

            for (int x : sumsList) {
                if (count.getOrDefault(x, 0) > 0) {
                    without.add(x);
                    count.put(x, count.get(x) - 1);

                    int y = x + diff;
                    if (count.getOrDefault(y, 0) <= 0) {
                        with = null;
                        break;
                    }

                    with.add(y);
                    count.put(y, count.get(y) - 1);
                }
            }

            if (without != null && without.contains(0)) {
                ans.add(diff);
                sumsList = without;
            } else {
                ans.add(-diff);
                sumsList = with;
            }
        }

        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = ans.get(i);
        }

        return result;
    }
}
