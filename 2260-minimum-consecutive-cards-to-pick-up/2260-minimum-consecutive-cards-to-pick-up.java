class Solution {
    public int minimumCardPickup(int[] cards) {
        Map<Integer, Integer> lastIndex = new HashMap<>();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < cards.length; i++) {
            if (lastIndex.containsKey(cards[i])) {
                min = Math.min(min, i - lastIndex.get(cards[i]) + 1);
            }
            lastIndex.put(cards[i], i);
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }
}