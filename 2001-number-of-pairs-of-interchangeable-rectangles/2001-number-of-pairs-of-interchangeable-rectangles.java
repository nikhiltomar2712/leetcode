class Solution {
    public long interchangeableRectangles(int[][] rectangles) {
        Map<String, Long> map = new HashMap<>();
        for (int[] rect : rectangles) {
            int g = gcd(rect[0], rect[1]);
            String key = (rect[0] / g) + "/" + (rect[1] / g);
            map.put(key, map.getOrDefault(key, 0L) + 1);
        }
        long res = 0;
        for (long count : map.values()) {
            res += count * (count - 1) / 2;
        }
        return res;
    }
    
    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}