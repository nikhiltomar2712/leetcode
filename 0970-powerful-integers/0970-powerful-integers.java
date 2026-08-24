class Solution {
    public List<Integer> powerfulIntegers(int x, int y, int bound) {
        Set<Integer> result = new HashSet<>();

        for (long a = 1; a <= bound; a *= x) {
            for (long b = 1; a + b <= bound; b *= y) {
                result.add((int) (a + b));
                if (y == 1) break;   // prevent infinite loop
            }
            if (x == 1) break;       // prevent infinite loop
        }

        return new ArrayList<>(result);
    }
}