class Solution {
    class BinaryIndexedTree {
        private int n;
        private int[] c;

        public BinaryIndexedTree(int n) {
            this.n = n;
            this.c = new int[n + 1];
        }

        public void update(int x, int delta) {
            for (; x <= n; x += x & -x) {
                c[x] += delta;
            }
        }

        public int query(int x) {
            int s = 0;
            for (; x > 0; x -= x & -x) {
                s += c[x];
            }
            return s;
        }
    }

    public int[] resultArray(int[] nums) {
        int n = nums.length;
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        Map<Integer, Integer> rank = new HashMap<>();
        int idx = 1;
        for (int num : sorted) {
            if (!rank.containsKey(num)) {
                rank.put(num, idx++);
            }
        }

        BinaryIndexedTree tree1 = new BinaryIndexedTree(idx);
        BinaryIndexedTree tree2 = new BinaryIndexedTree(idx);

        List<Integer> arr1 = new ArrayList<>();
        List<Integer> arr2 = new ArrayList<>();

        arr1.add(nums[0]);
        tree1.update(rank.get(nums[0]), 1);
        arr2.add(nums[1]);
        tree2.update(rank.get(nums[1]), 1);

        for (int i = 2; i < n; i++) {
            int r = rank.get(nums[i]);

            int count1 = tree1.query(r);
            int count2 = tree2.query(r);

            int greater1 = arr1.size() - count1;
            int greater2 = arr2.size() - count2;

            if (greater1 > greater2) {
                arr1.add(nums[i]);
                tree1.update(r, 1);
            } else if (greater1 < greater2) {
                arr2.add(nums[i]);
                tree2.update(r, 1);
            } else {
                if (arr1.size() <= arr2.size()) {
                    arr1.add(nums[i]);
                    tree1.update(r, 1);
                } else {
                    arr2.add(nums[i]);
                    tree2.update(r, 1);
                }
            }
        }

        int[] result = new int[n];
        int pos = 0;
        for (int num : arr1) result[pos++] = num;
        for (int num : arr2) result[pos++] = num;
        return result;
    }
}