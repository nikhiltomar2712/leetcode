class Solution {
    public int[] findOriginalArray(int[] changed) {
        if (changed.length % 2 != 0) return new int[0];
        Arrays.sort(changed);
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : changed) freq.put(num, freq.getOrDefault(num, 0) + 1);
        List<Integer> res = new ArrayList<>();
        for (int num : changed) {
            if (freq.getOrDefault(num, 0) == 0) continue;
            if (freq.getOrDefault(num * 2, 0) == 0) return new int[0];
            res.add(num);
            freq.put(num, freq.get(num) - 1);
            freq.put(num * 2, freq.get(num * 2) - 1);
        }
        return res.stream().mapToInt(i -> i).toArray();
    }
}