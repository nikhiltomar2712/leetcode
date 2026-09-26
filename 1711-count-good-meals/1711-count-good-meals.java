class Solution {
    public int countPairs(int[] deliciousness) {
        int MOD = 1_000_000_007;
        HashMap<Integer, Integer> map = new HashMap<>();
        long count = 0;

        for (int d : deliciousness) {
            int power = 1;
            for (int i = 0; i <= 21; i++) {
                int target = power - d;
                if (map.containsKey(target)) {
                    count += map.get(target);
                }
                power <<= 1;
            }
            map.put(d, map.getOrDefault(d, 0) + 1);
        }

        return (int) (count % MOD);
    }
}