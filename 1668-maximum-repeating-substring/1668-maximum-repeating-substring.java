class Solution {
    public int maxRepeating(String sequence, String word) {
        int k = 0;
        String repeated = word;

        while (sequence.contains(repeated)) {
            k++;
            repeated += word;
        }

        return k;
    }
}