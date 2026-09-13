import java.util.*;

class TweetCounts {
    // Map: tweetName -> TreeMap<timestamp, count>
    private Map<String, TreeMap<Integer, Integer>> tweetMap;

    public TweetCounts() {
        tweetMap = new HashMap<>();
    }

    public void recordTweet(String tweetName, int time) {
        tweetMap.putIfAbsent(tweetName, new TreeMap<>());
        TreeMap<Integer, Integer> times = tweetMap.get(tweetName);
        times.put(time, times.getOrDefault(time, 0) + 1);
    }

    public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName,
                                                     int startTime, int endTime) {
        List<Integer> result = new ArrayList<>();

        // If no tweets recorded for this name, return empty chunks
        if (!tweetMap.containsKey(tweetName)) {
            int interval = getInterval(freq);
            for (int start = startTime; start <= endTime; start += interval) {
                result.add(0);
            }
            return result;
        }

        TreeMap<Integer, Integer> times = tweetMap.get(tweetName);
        int interval = getInterval(freq);

        // Iterate over each chunk
        for (int start = startTime; start <= endTime; start += interval) {
            int end = Math.min(start + interval - 1, endTime);

            // subMap is [start, end+1) to include end
            Map<Integer, Integer> sub = times.subMap(start, true, end, true);
            int count = 0;
            for (int c : sub.values()) {
                count += c;
            }
            result.add(count);
        }

        return result;
    }

    private int getInterval(String freq) {
        switch (freq) {
            case "minute": return 60;
            case "hour":   return 3600;
            case "day":    return 86400;
            default:       return 60;
        }
    }
}