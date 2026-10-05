class Solution {
    public int maxTaskAssign(int[] tasks, int[] workers, int pills, int strength) {
        Arrays.sort(tasks);
        Arrays.sort(workers);
        int left = 0, right = Math.min(tasks.length, workers.length);
        while (left < right) {
            int mid = (left + right + 1) / 2;
            if (canAssign(tasks, workers, pills, strength, mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private boolean canAssign(int[] tasks, int[] workers, int pills, int strength, int k) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = workers.length - k; i < workers.length; i++) {
            map.merge(workers[i], 1, Integer::sum);
        }
        for (int i = k - 1; i >= 0; i--) {
            int task = tasks[i];
            Integer key = map.ceilingKey(task);
            if (key != null) {
                remove(map, key);
            } else {
                if (pills == 0) return false;
                Integer key2 = map.ceilingKey(task - strength);
                if (key2 == null) return false;
                remove(map, key2);
                pills--;
            }
        }
        return true;
    }

    private void remove(TreeMap<Integer, Integer> map, int key) {
        map.merge(key, -1, Integer::sum);
        if (map.get(key) == 0) map.remove(key);
    }
}