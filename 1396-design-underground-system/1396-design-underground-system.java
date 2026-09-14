import java.util.*;

class UndergroundSystem {
    
    // id -> [startStation, checkInTime]
    private Map<Integer, Object[]> checkInMap;
    
    // "startStation->endStation" -> [totalTime, tripCount]
    private Map<String, int[]> routeStats;
    
    public UndergroundSystem() {
        checkInMap = new HashMap<>();
        routeStats = new HashMap<>();
    }
    
    public void checkIn(int id, String stationName, int t) {
        checkInMap.put(id, new Object[]{stationName, t});
    }
    
    public void checkOut(int id, String stationName, int t) {
        Object[] checkInInfo = checkInMap.remove(id);
        String startStation = (String) checkInInfo[0];
        int startTime = (int) checkInInfo[1];
        int duration = t - startTime;
        
        String key = startStation + "->" + stationName;
        int[] stats = routeStats.getOrDefault(key, new int[]{0, 0});
        stats[0] += duration;  // total time
        stats[1] += 1;         // trip count
        routeStats.put(key, stats);
    }
    
    public double getAverageTime(String startStation, String endStation) {
        String key = startStation + "->" + endStation;
        int[] stats = routeStats.get(key);
        return (double) stats[0] / stats[1];
    }
}