class Solution {
    public int numDifferentIntegers(String word) {
        Set<String> set = new HashSet<>();
        int i = 0, n = word.length();
        while (i < n) {
            if (!Character.isDigit(word.charAt(i))) {
                i++;
                continue;
            }
            int j = i;
            while (j < n && Character.isDigit(word.charAt(j))) j++;
            String num = word.substring(i, j).replaceFirst("^0+", "");
            set.add(num.isEmpty() ? "0" : num);
            i = j;
        }
        return set.size();
    }
}