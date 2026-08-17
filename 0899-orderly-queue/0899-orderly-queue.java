class Solution {
    public String orderlyQueue(String s, int k) {
        // If k > 1, we can sort the string completely
        if (k > 1) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            return new String(chars);
        }
        
        // If k == 1, we can only rotate the string
        // Find the lexicographically smallest rotation
        String smallest = s;
        for (int i = 1; i < s.length(); i++) {
            String rotation = s.substring(i) + s.substring(0, i);
            if (rotation.compareTo(smallest) < 0) {
                smallest = rotation;
            }
        }
        return smallest;
    }
}