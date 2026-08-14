class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0) {
            return false;
        }
        
        // Count frequency of each card value
        TreeMap<Integer, Integer> cardCount = new TreeMap<>();
        for (int card : hand) {
            cardCount.put(card, cardCount.getOrDefault(card, 0) + 1);
        }
        
        // Process cards in ascending order
        while (!cardCount.isEmpty()) {
            int first = cardCount.firstKey();
            
            // Try to form a group starting with 'first'
            for (int i = 0; i < groupSize; i++) {
                int current = first + i;
                if (!cardCount.containsKey(current)) {
                    return false;
                }
                
                // Decrease count, remove if zero
                int count = cardCount.get(current);
                if (count == 1) {
                    cardCount.remove(current);
                } else {
                    cardCount.put(current, count - 1);
                }
            }
        }
        
        return true;
    }
}