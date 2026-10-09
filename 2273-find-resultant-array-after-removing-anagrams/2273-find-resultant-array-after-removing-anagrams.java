class Solution {
    public List<String> removeAnagrams(String[] words) {
        List<String> res = new ArrayList<>();
        String prev = "";
        for (String w : words) {
            char[] arr = w.toCharArray();
            Arrays.sort(arr);
            String sorted = new String(arr);
            if (!sorted.equals(prev)) {
                res.add(w);
                prev = sorted;
            }
        }
        return res;
    }
}