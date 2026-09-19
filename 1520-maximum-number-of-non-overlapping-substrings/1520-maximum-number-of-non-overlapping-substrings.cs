public class Solution {
    public IList<string> MaxNumOfSubstrings(string s) {
        int n = s.Length;

        // Step 1: first and last occurrence of each letter
        var first = new int[26];
        var last = new int[26];
        Array.Fill(first, int.MaxValue);
        Array.Fill(last, -1);

        for (int i = 0; i < n; i++) {
            int c = s[i] - 'a';
            first[c] = Math.Min(first[c], i);
            last[c] = i;
        }

        // Step 2: For each index, find the smallest valid window starting there
        var intervals = new List<(int start, int end)>();

        for (int i = 0; i < n; i++) {
            if (i != first[s[i] - 'a']) continue;  // must start at first occurrence

            int end = last[s[i] - 'a'];
            for (int j = i; j <= end; j++) {
                int c = s[j] - 'a';
                if (first[c] < i) { end = -1; break; }  // a needed letter starts before i → invalid
                end = Math.Max(end, last[c]);           // expand window
            }

            if (end != -1)
                intervals.Add((i, end));
        }

        // Step 3: Greedy interval scheduling — sort by end, pick non-overlapping
        intervals.Sort((a, b) => a.end.CompareTo(b.end));

        var result = new List<string>();
        int lastEnd = -1;
        foreach (var (start, end) in intervals) {
            if (start > lastEnd) {
                result.Add(s.Substring(start, end - start + 1));
                lastEnd = end;
            }
        }

        return result;
    }
}