class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        int n = words.length;
        int i = 0;
        while (i < n) {
            int j = i, len = 0;
            while (j < n && len + words[j].length() + (j - i) <= maxWidth) {
                len += words[j].length();
                j++;
            }
            StringBuilder sb = new StringBuilder();
            int gaps = j - i - 1;
            if (j == n || gaps == 0) {
                for (int k = i; k < j; k++) {
                    sb.append(words[k]);
                    if (k < j - 1) sb.append(" ");
                }
                while (sb.length() < maxWidth) sb.append(" ");
            } else {
                int totalSpaces = maxWidth - len;
                int base = totalSpaces / gaps;
                int extra = totalSpaces % gaps;
                for (int k = i; k < j; k++) {
                    sb.append(words[k]);
                    if (k < j - 1) {
                        int spaces = base + (k - i < extra ? 1 : 0);
                        for (int s = 0; s < spaces; s++) sb.append(" ");
                    }
                }
            }
            res.add(sb.toString());
            i = j;
        }
        return res;
    }
}