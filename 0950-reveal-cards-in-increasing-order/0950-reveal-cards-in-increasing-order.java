class Solution {
    public int[] deckRevealedIncreasing(int[] deck) {
        int n = deck.length;
        Arrays.sort(deck);                    // Sort the deck in increasing order
        
        Deque<Integer> queue = new ArrayDeque<>();
        
        // Simulate the reverse process
        for (int i = n - 1; i >= 0; i--) {
            if (!queue.isEmpty()) {
                // Move the last card to the front (reverse of putting top to bottom)
                queue.addFirst(queue.removeLast());
            }
            // Place the next largest card at the front
            queue.addFirst(deck[i]);
        }
        
        // Convert deque to array
        int[] result = new int[n];
        int idx = 0;
        for (int card : queue) {
            result[idx++] = card;
        }
        
        return result;
    }
}