class MagicDictionary {

    // length → list of words of that length
    private Map<Integer, List<String>> map;

    public MagicDictionary() {
        map = new HashMap<>();
    }

    public void buildDict(String[] dictionary) {
        for (String word : dictionary) {
            map.computeIfAbsent(word.length(), k -> new ArrayList<>()).add(word);
        }
    }

    public boolean search(String searchWord) {
        List<String> candidates = map.get(searchWord.length());
        if (candidates == null) return false;

        for (String word : candidates) {
            if (diffExactlyOne(word, searchWord)) {
                return true;
            }
        }
        return false;
    }

    private boolean diffExactlyOne(String a, String b) {
        int diff = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                if (++diff > 1) return false;
            }
        }
        return diff == 1;
    }
}