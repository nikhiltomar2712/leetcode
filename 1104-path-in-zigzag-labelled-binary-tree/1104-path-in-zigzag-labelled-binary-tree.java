class Solution {
    public List<Integer> pathInZigZagTree(int label) {
        LinkedList<Integer> path = new LinkedList<>();
        
        while (label > 0) {
            path.addFirst(label);
            
            // Find the highest power of 2 <= label → level start
            int levelStart = Integer.highestOneBit(label);
            
            // Mirror the label inside its level, then go to parent
            label = (levelStart * 3 - 1 - label) / 2;
        }
        
        return path;
    }
}