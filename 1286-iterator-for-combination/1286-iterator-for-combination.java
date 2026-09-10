class CombinationIterator {
    private List<String> combinations;
    private int index;

    public CombinationIterator(String characters, int combinationLength) {
        combinations = new ArrayList<>();
        generate(characters, combinationLength, 0, new StringBuilder());
        index = 0;
    }
    
    private void generate(String chars, int len, int start, StringBuilder sb) {
        if (sb.length() == len) {
            combinations.add(sb.toString());
            return;
        }
        for (int i = start; i < chars.length(); i++) {
            sb.append(chars.charAt(i));
            generate(chars, len, i + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
    
    public String next() {
        return combinations.get(index++);
    }
    
    public boolean hasNext() {
        return index < combinations.size();
    }
}

/**
 * Your CombinationIterator object will be instantiated and called as such:
 * CombinationIterator obj = new CombinationIterator(characters, combinationLength);
 * String param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */