class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> freq = new HashMap<>();
        for (String w : words) {
            freq.put(w, freq.getOrDefault(w, 0) + 1);
        }

        List<String> candidates = new ArrayList<>(freq.keySet());
        candidates.sort((a, b) -> {
            int fa = freq.get(a), fb = freq.get(b);
            if (fa != fb) {
                return fb - fa;          // higher frequency first
            }
            return a.compareTo(b);       // lexicographical order
        });

        return candidates.subList(0, k);
    }
}