class Solution {
    public int slidingPuzzle(int[][] board) {
        // Target state
        String target = "123450";
        
        // Convert initial board to string
        StringBuilder start = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                start.append(board[i][j]);
            }
        }
        String startState = start.toString();
        
        // If already solved
        if (startState.equals(target)) {
            return 0;
        }
        
        // BFS
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(startState);
        visited.add(startState);
        
        // Possible moves for each position (index in string)
        // positions: 0 1 2
        //            3 4 5
        int[][] moves = {
            {1, 3},       // position 0 can swap with 1, 3
            {0, 2, 4},    // position 1 can swap with 0, 2, 4
            {1, 5},       // position 2 can swap with 1, 5
            {0, 4},       // position 3 can swap with 0, 4
            {1, 3, 5},    // position 4 can swap with 1, 3, 5
            {2, 4}        // position 5 can swap with 2, 4
        };
        
        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            steps++;
            
            for (int i = 0; i < size; i++) {
                String current = queue.poll();
                int zeroPos = current.indexOf('0');
                
                // Try all possible moves from current zero position
                for (int nextPos : moves[zeroPos]) {
                    String nextState = swap(current, zeroPos, nextPos);
                    
                    if (nextState.equals(target)) {
                        return steps;
                    }
                    
                    if (!visited.contains(nextState)) {
                        visited.add(nextState);
                        queue.offer(nextState);
                    }
                }
            }
        }
        
        return -1; // No solution
    }
    
    private String swap(String s, int i, int j) {
        char[] chars = s.toCharArray();
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
        return new String(chars);
    }
}