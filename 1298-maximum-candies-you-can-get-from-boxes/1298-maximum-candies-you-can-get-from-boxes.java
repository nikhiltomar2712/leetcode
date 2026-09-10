class Solution {
    public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        int n = status.length;
        boolean[] hasBox = new boolean[n];
        boolean[] hasKey = new boolean[n];
        boolean[] opened = new boolean[n];
        
        Queue<Integer> queue = new LinkedList<>();
        
        // Initially we have these boxes
        for (int box : initialBoxes) {
            hasBox[box] = true;
            // If the box is already open (status == 1), we can process it immediately
            if (status[box] == 1) {
                queue.offer(box);
            }
        }
        
        int totalCandies = 0;
        
        while (!queue.isEmpty()) {
            int box = queue.poll();
            if (opened[box]) continue;
            opened[box] = true;
            
            // Collect candies
            totalCandies += candies[box];
            
            // Obtain keys found in this box
            for (int key : keys[box]) {
                hasKey[key] = true;
                // If we already have the box and it wasn't opened yet, we can open it now
                if (hasBox[key] && !opened[key]) {
                    queue.offer(key);
                }
            }
            
            // Obtain boxes found inside this box
            for (int contained : containedBoxes[box]) {
                hasBox[contained] = true;
                // We can open it if:
                // - it is initially open (status == 1), or
                // - we already have a key for it
                if ((status[contained] == 1 || hasKey[contained]) && !opened[contained]) {
                    queue.offer(contained);
                }
            }
        }
        
        return totalCandies;
    }
}