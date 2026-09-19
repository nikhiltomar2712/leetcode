public class Solution {
    public int ClosestToTarget(int[] arr, int target) {
        int best = int.MaxValue;
        var prev = new HashSet<int>();  // distinct AND values of subarrays ending at previous index

        foreach (int x in arr) {
            var curr = new HashSet<int> { x };

            foreach (int v in prev)
                curr.Add(v & x);

            foreach (int v in curr)
                best = Math.Min(best, Math.Abs(v - target));

            prev = curr;

            if (best == 0) return 0;  // can't do better
        }

        return best;
    }
}