class Solution {
    public boolean isSumEqual(String firstWord, String secondWord, String targetWord) {
        return getValue(firstWord) + getValue(secondWord) == getValue(targetWord);
    }

    private int getValue(String word) {
        int val = 0;
        for (char c : word.toCharArray()) {
            val = val * 10 + (c - 'a');
        }
        return val;
    }
}