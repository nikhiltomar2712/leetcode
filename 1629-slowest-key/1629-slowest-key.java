class Solution {
    public char slowestKey(int[] releaseTimes, String keysPressed) {
        char resultKey = keysPressed.charAt(0);
        int maxDuration = releaseTimes[0];

        for (int i = 1; i < keysPressed.length(); i++) {
            int duration = releaseTimes[i] - releaseTimes[i - 1];
            char key = keysPressed.charAt(i);

            if (duration > maxDuration) {
                maxDuration = duration;
                resultKey = key;
            } else if (duration == maxDuration && key > resultKey) {
                resultKey = key;
            }
        }

        return resultKey;
    }
}