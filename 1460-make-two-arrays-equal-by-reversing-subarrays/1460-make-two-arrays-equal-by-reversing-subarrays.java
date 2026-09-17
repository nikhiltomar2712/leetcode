class Solution {
    public boolean canBeEqual(int[] target, int[] arr) {
        Map<Integer, Integer> count = new HashMap<>();

        for (int num : target) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        for (int num : arr) {
            if (!count.containsKey(num) || count.get(num) == 0) {
                return false;
            }
            count.put(num, count.get(num) - 1);
        }

        return true;
    }
}