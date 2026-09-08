class Solution {
    public List<Integer> circularPermutation(int n, int start) {
        List<Integer> result = new ArrayList<>();
        
        // Generate Gray code sequence for n bits
        // Gray code: i ^ (i >> 1)
        for (int i = 0; i < (1 << n); i++) {
            int grayCode = i ^ (i >> 1);
            result.add(grayCode);
        }
        
        // Rotate the sequence so that it starts with 'start'
        // Find the index of 'start' in the Gray code sequence
        int startIndex = 0;
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i) == start) {
                startIndex = i;
                break;
            }
        }
        
        // Rotate: take sublist from startIndex to end, then from 0 to startIndex-1
        List<Integer> rotated = new ArrayList<>();
        for (int i = startIndex; i < result.size(); i++) {
            rotated.add(result.get(i));
        }
        for (int i = 0; i < startIndex; i++) {
            rotated.add(result.get(i));
        }
        
        return rotated;
    }
}