class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        String res = "";
        int left = 0;
        int count = 0;
        for (int right = 0; right < n; right++) {
            if (s.charAt(right) == '1') count++;
            while (count > k) {
                if (s.charAt(left) == '1') count--;
                left++;
            }
            if (count == k) {
                while (s.charAt(left) == '0') left++;
                String candidate = s.substring(left, right + 1);
                if (res.isEmpty() || candidate.length() < res.length() ||
                    (candidate.length() == res.length() && candidate.compareTo(res) < 0)) {
                    res = candidate;
                }
            }
        }
        return res;
    }
}