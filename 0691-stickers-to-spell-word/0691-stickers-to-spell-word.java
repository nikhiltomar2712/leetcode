class Solution {
    public int minStickers(String[] stickers, String target) {
        int n = target.length();
        int N = 1 << n;                     // 2^n states
        int[] dp = new int[N];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        // Precompute letter counts of each sticker
        int[][] stickerCnt = new int[stickers.length][26];
        for (int i = 0; i < stickers.length; i++) {
            for (char c : stickers[i].toCharArray()) {
                stickerCnt[i][c - 'a']++;
            }
        }

        for (int mask = 0; mask < N; mask++) {
            if (dp[mask] == Integer.MAX_VALUE) continue;

            // Try every sticker
            for (int[] cnt : stickerCnt) {
                int newMask = mask;
                int[] need = cnt.clone();   // remaining letters of this sticker

                for (int i = 0; i < n; i++) {
                    if ((mask & (1 << i)) != 0) continue; // already covered
                    char c = target.charAt(i);
                    if (need[c - 'a'] > 0) {
                        need[c - 'a']--;
                        newMask |= (1 << i);
                    }
                }

                if (newMask != mask) {
                    dp[newMask] = Math.min(dp[newMask], dp[mask] + 1);
                }
            }
        }

        return dp[N - 1] == Integer.MAX_VALUE ? -1 : dp[N - 1];
    }
}