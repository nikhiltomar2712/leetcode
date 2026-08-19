import java.util.Arrays;

class TopVotedCandidate {
    private int[] times;
    private int[] leaders;  // leaders[i] = person leading after the i-th vote

    public TopVotedCandidate(int[] persons, int[] times) {
        this.times = times;
        int n = persons.length;
        this.leaders = new int[n];
        
        int[] count = new int[n];  // vote count for each person (persons[i] < n)
        int leader = -1;
        int maxVotes = 0;
        
        for (int i = 0; i < n; i++) {
            int p = persons[i];
            count[p]++;
            
            // Update leader if this person now has >= maxVotes
            // (tie → most recent vote wins)
            if (count[p] >= maxVotes) {
                maxVotes = count[p];
                leader = p;
            }
            
            leaders[i] = leader;
        }
    }
    
    public int q(int t) {
        // Binary search for the rightmost index where times[i] <= t
        int left = 0, right = times.length - 1;
        
        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            if (times[mid] <= t) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        
        return leaders[left];
    }
}

/**
 * Your TopVotedCandidate object will be instantiated and called as such:
 * TopVotedCandidate obj = new TopVotedCandidate(persons, times);
 * int param_1 = obj.q(t);
 */