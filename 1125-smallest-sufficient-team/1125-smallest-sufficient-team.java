class Solution {
    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        int m = req_skills.length;
        int n = people.size();
        
        // Map each skill to its bit index
        Map<String, Integer> skillIndex = new HashMap<>();
        for (int i = 0; i < m; i++) {
            skillIndex.put(req_skills[i], i);
        }
        
        // Convert each person's skills to a bitmask
        int[] personMask = new int[n];
        for (int i = 0; i < n; i++) {
            int mask = 0;
            for (String skill : people.get(i)) {
                mask |= 1 << skillIndex.get(skill);
            }
            personMask[i] = mask;
        }
        
        // DP: dp[mask] = bitmask of people that form the smallest team for this skill mask
        long[] dp = new long[1 << m];
        Arrays.fill(dp, (1L << n) - 1); // Initialize with all people (max possible)
        dp[0] = 0; // No skills needed -> empty team
        
        // For each person, try to improve all existing masks
        for (int i = 0; i < n; i++) {
            int pMask = personMask[i];
            if (pMask == 0) continue; // Skip people with no skills
            
            // Iterate over all current masks
            for (int mask = 0; mask < (1 << m); mask++) {
                if (dp[mask] == (1L << n) - 1) continue; // Skip unreachable states
                
                // New mask after adding this person
                int newMask = mask | pMask;
                // New team: current team + this person
                long newTeam = dp[mask] | (1L << i);
                
                // Update if this team is smaller (fewer people)
                if (Long.bitCount(newTeam) < Long.bitCount(dp[newMask])) {
                    dp[newMask] = newTeam;
                }
            }
        }
        
        // Extract the result from dp[allSkillsMask]
        long teamMask = dp[(1 << m) - 1];
        int[] result = new int[Long.bitCount(teamMask)];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            if ((teamMask & (1L << i)) != 0) {
                result[idx++] = i;
            }
        }
        return result;
    }
}