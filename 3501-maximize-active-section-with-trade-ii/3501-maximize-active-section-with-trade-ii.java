import java.util.*;

class Solution {
    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int n = s.length();
        int ones = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') ones++;
        }

        ZeroGroupsResult zgResult = getZeroGroups(s);
        List<Group> zeroGroups = zgResult.zeroGroups;
        int[] zeroGroupIndex = zgResult.zeroGroupIndex;

        if (zeroGroups.isEmpty()) {
            List<Integer> ans = new ArrayList<>();
            for (int i = 0; i < queries.length; i++) {
                ans.add(ones);
            }
            return ans;
        }

        int[] adjacentSums = getAdjacentGroupLengthSums(zeroGroups);
        SparseTable st = new SparseTable(adjacentSums);

        List<Integer> answer = new ArrayList<>();
        for (int q = 0; q < queries.length; q++) {
            int l = queries[q][0];
            int r = queries[q][1];

            int leftPartial = (zeroGroupIndex[l] == -1) ? -1
                : (zeroGroups.get(zeroGroupIndex[l]).length - (l - zeroGroups.get(zeroGroupIndex[l]).start));

            int rightPartial = (zeroGroupIndex[r] == -1) ? -1
                : (r - zeroGroups.get(zeroGroupIndex[r]).start + 1);

            int startAdjIdx = zeroGroupIndex[l] + 1;
            int endAdjIdx = (s.charAt(r) == '1' ? zeroGroupIndex[r] : zeroGroupIndex[r] - 1);
            Pair adjPair = mapToAdjacentGroupIndices(startAdjIdx, endAdjIdx);
            int startAdjacentGroupIndex = adjPair.first;
            int endAdjacentGroupIndex = adjPair.second;

            int activeSections = ones;

            if (s.charAt(l) == '0' && s.charAt(r) == '0' &&
                zeroGroupIndex[l] + 1 == zeroGroupIndex[r]) {
                activeSections = Math.max(activeSections, ones + leftPartial + rightPartial);
            } else if (startAdjacentGroupIndex <= endAdjacentGroupIndex) {
                activeSections = Math.max(activeSections,
                    ones + st.query(startAdjacentGroupIndex, endAdjacentGroupIndex));
            }

            if (s.charAt(l) == '0' &&
                zeroGroupIndex[l] + 1 <= (s.charAt(r) == '1' ? zeroGroupIndex[r] : zeroGroupIndex[r] - 1)) {
                activeSections = Math.max(activeSections,
                    ones + leftPartial + zeroGroups.get(zeroGroupIndex[l] + 1).length);
            }

            if (s.charAt(r) == '0' && zeroGroupIndex[l] < zeroGroupIndex[r] - 1) {
                activeSections = Math.max(activeSections,
                    ones + rightPartial + zeroGroups.get(zeroGroupIndex[r] - 1).length);
            }

            answer.add(activeSections);
        }
        return answer;
    }

    private ZeroGroupsResult getZeroGroups(String s) {
        List<Group> zeroGroups = new ArrayList<>();
        int[] zeroGroupIndex = new int[s.length()];
        Arrays.fill(zeroGroupIndex, -1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                if (!zeroGroups.isEmpty() && i > 0 && s.charAt(i - 1) == '0') {
                    zeroGroups.get(zeroGroups.size() - 1).length++;
                } else {
                    zeroGroups.add(new Group(i, 1));
                }
            }
            zeroGroupIndex[i] = zeroGroups.isEmpty() ? -1 : zeroGroups.size() - 1;
        }
        return new ZeroGroupsResult(zeroGroups, zeroGroupIndex);
    }

    private int[] getAdjacentGroupLengthSums(List<Group> zeroGroups) {
        if (zeroGroups.size() <= 1) return new int[0];
        int[] sums = new int[zeroGroups.size() - 1];
        for (int i = 0; i < sums.length; i++) {
            sums[i] = zeroGroups.get(i).length + zeroGroups.get(i + 1).length;
        }
        return sums;
    }

    private Pair mapToAdjacentGroupIndices(int startGroupIndex, int endGroupIndex) {
        return new Pair(startGroupIndex, endGroupIndex - 1);
    }

    static class Group {
        int start;
        int length;

        Group(int start, int length) {
            this.start = start;
            this.length = length;
        }
    }

    static class ZeroGroupsResult {
        List<Group> zeroGroups;
        int[] zeroGroupIndex;

        ZeroGroupsResult(List<Group> zg, int[] zgi) {
            this.zeroGroups = zg;
            this.zeroGroupIndex = zgi;
        }
    }

    static class Pair {
        int first, second;

        Pair(int f, int s) {
            first = f;
            second = s;
        }
    }

    static class SparseTable {
        private int n;
        private int[][] st;

        SparseTable(int[] nums) {
            n = nums.length;
            if (n == 0) return;
            int log = 32 - Integer.numberOfLeadingZeros(n);
            st = new int[log + 1][n];
            for (int i = 0; i < n; i++) {
                st[0][i] = nums[i];
            }
            for (int i = 1; i <= log; i++) {
                for (int j = 0; j + (1 << i) <= n; j++) {
                    st[i][j] = Math.max(st[i - 1][j], st[i - 1][j + (1 << (i - 1))]);
                }
            }
        }

        int query(int l, int r) {
            if (l > r || n == 0) return 0;
            int len = r - l + 1;
            int i = 31 - Integer.numberOfLeadingZeros(len);
            return Math.max(st[i][l], st[i][r - (1 << i) + 1]);
        }
    }
}