class Solution {
    public String rankTeams(String[] votes) {
        int n = votes[0].length();
        Map<Character, int[]> rank = new HashMap<>();
        
        for (String vote : votes) {
            for (int i = 0; i < n; i++) {
                char c = vote.charAt(i);
                rank.putIfAbsent(c, new int[n]);
                rank.get(c)[i]++;
            }
        }
        
        List<Character> teams = new ArrayList<>();
        for (char c : votes[0].toCharArray()) {
            teams.add(c);
        }
        
        teams.sort((a, b) -> {
            int[] rankA = rank.get(a);
            int[] rankB = rank.get(b);
            for (int i = 0; i < n; i++) {
                if (rankA[i] != rankB[i]) {
                    return rankB[i] - rankA[i]; // descending
                }
            }
            return a - b; // alphabetical
        });
        
        StringBuilder sb = new StringBuilder();
        for (char c : teams) {
            sb.append(c);
        }
        return sb.toString();
    }
}