class Solution {
    public int minChanges(String s) {
        int changes = 0;
        // 每两个字符为一组，检查是否需要修改
        for (int i = 0; i < s.length(); i += 2) {
            if (s.charAt(i) != s.charAt(i + 1)) {
                changes++;
            }
        }
        return changes;
    }
}