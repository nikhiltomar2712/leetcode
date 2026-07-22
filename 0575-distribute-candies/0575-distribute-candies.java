class Solution {
    public int distributeCandies(int[] candyType) {
        Set<Integer> unique = new HashSet<>();
        for (int type : candyType) {
            unique.add(type);
        }
        return Math.min(unique.size(), candyType.length / 2);
    }
}