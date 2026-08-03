class Solution {
    public String customSortString(String order, String s) {
        int[] rank = new int[26];
        // Default rank is 0; characters in order get positive ranks
        for (int i = 0; i < order.length(); i++) {
            rank[order.charAt(i) - 'a'] = i + 1;
        }

        Character[] arr = new Character[s.length()];
        for (int i = 0; i < s.length(); i++) {
            arr[i] = s.charAt(i);
        }

        Arrays.sort(arr, (a, b) -> rank[a - 'a'] - rank[b - 'a']);

        StringBuilder sb = new StringBuilder();
        for (char c : arr) {
            sb.append(c);
        }
        return sb.toString();
    }
}