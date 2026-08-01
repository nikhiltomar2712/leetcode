class Solution {
    public String makeLargestSpecial(String s) {
        List<String> specials = new ArrayList<>();
        int count = 0, start = 0;
        
        for (int i = 0; i < s.length(); i++) {
            count += (s.charAt(i) == '1') ? 1 : -1;
            
            // Found a complete special substring
            if (count == 0) {
                // Recursively process the inside part (without the outer 1 and 0)
                String inside = makeLargestSpecial(s.substring(start + 1, i));
                specials.add("1" + inside + "0");
                start = i + 1;
            }
        }
        
        // Sort the special substrings in reverse lexicographical order
        Collections.sort(specials, Collections.reverseOrder());
        
        // Concatenate them
        StringBuilder sb = new StringBuilder();
        for (String t : specials) {
            sb.append(t);
        }
        return sb.toString();
    }
}